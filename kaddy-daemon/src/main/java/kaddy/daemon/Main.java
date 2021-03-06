/*
 * This file is part of Kaddy.
 *
 * Copyright (C) 2018  Kaddy Team
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package kaddy.daemon;

import dtmlibs.config.datasource.DataHandlingException;
import kaddy.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static final boolean windows = System.getProperty("os.name").startsWith("Windows");
    private static final Path botStopPath = Paths.get("./.bot-stop");
    private static final Path configDir = Paths.get(System.getProperty("user.home"), ".kaddy");

    public static void main(String[] args) {

        Config config = new Config();

        if (config.isFileCreated()) {
            try {
                config.load();
            } catch (DataHandlingException e) {
                throw new RuntimeException(e);
            }
        } else {
            // First run
            try {
                config.createConfigFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            guidedSetup(config);
            System.out.println("Bot has been configured! Rerun the command to start the bot.");
            return;
        }

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
                Process prepare = new ProcessBuilder(windows ? "gradlew.bat" : "./gradlew", "configureDependencies", "configureRuntime", "configureSamplePlugins").inheritIO().start();
                if (prepare.waitFor() != 0) {
                    break;
                }
                ProcessBuilder pb = new ProcessBuilder(getBotCommand(config));
                System.out.println(pb.command());
                Process bot = pb.inheritIO().start();
                if (bot.waitFor() != 0) {
                    break;
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (Files.exists(botStopPath)) {
                try {
                    Files.delete(botStopPath);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void guidedSetup(Config config) {
        Scanner in = new Scanner(System.in);

        askForConfigValue(in, "Command to start java (" + config.getJavaCmd() + "): ",
                config::setJavaCmd);

        askForConfigValue(in, "Desired jvm args (" + convertListToString(config.getJvmArgs()) + "): ",
                v -> {config.setJvmArgs(convertStringToList(v));});

        askForConfigValue(in, "Discord bot API token (" + config.getBotToken() + "): ",
                config::setBotToken);

        askForConfigValue(in, "Default command prefix (" + config.getDefaultCommandPrefix() + "): ",
                config::setDefaultCommandPrefix);

        try {
            config.save();
        } catch (DataHandlingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void askForConfigValue(Scanner in, String prompt, Consumer<String> setter) {
        System.out.print(prompt);
        String line = in.nextLine();
        if (!line.isEmpty()) {
            setter.accept(line);
        }
    }

    private static String convertListToString(List<String> list) {
        StringBuilder b = new StringBuilder();
        for (String s : list) {
            if (b.length() != 0) {
                b.append(" ");
            }
            b.append(s);
        }
        return b.toString();
    }

    private static List<String> convertStringToList(String s) {
        return new ArrayList<>(Arrays.asList(s.split("\\s+")));
    }

    private static List<String> getBotCommand(Config config) {
        List<String> procArgs = new ArrayList<>();
        procArgs.add(config.getJavaCmd());
        procArgs.addAll(config.getJvmArgs());
        procArgs.add("-cp");
        procArgs.add("./runtime/*"+ (windows ? ";" : ":") +"./libs/*");
        procArgs.add("kaddy.KaddyBot");
        procArgs.add(config.getBotToken());
        return procArgs;
    }
}
