/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2017  Kaddy Team
 *
 * Kaddy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kaddy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Kaddy.  If not, see <http://www.gnu.org/licenses/>.
 */
package kaddy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Daemon {

    public static boolean windows = System.getProperty("os.name").startsWith("Windows");

    public static void main(String[] args) {
        List<String> procArgs = new ArrayList<>();
        procArgs.add("java");
        procArgs.add("-cp");
        procArgs.add("./runtime/*"+ (windows ? ";" : ":") +"./libs/*");
        procArgs.add("kaddy.KaddyBot");
        procArgs.addAll(Arrays.asList(args));
        while(true) {
            try {
//                Process git = new ProcessBuilder("git", "pull").inheritIO().start();
//                if (git.waitFor() != 0) {
//                    break;
//                }
//                Process build = new ProcessBuilder("gradlew.bat", "clean", "build").inheritIO().start();
//                if (build.waitFor() != 0) {
//                    break;
//                }
                Process prepare = new ProcessBuilder(windows ? "gradlew.bat" : "./gradlew", "configureDependencies", "configureRuntime").inheritIO().start();
                if (prepare.waitFor() != 0) {
                    break;
                }
                ProcessBuilder pb = new ProcessBuilder(procArgs);
                System.out.println(pb.command());
                Process bot = pb.inheritIO().start();
                if (bot.waitFor() != 0) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
