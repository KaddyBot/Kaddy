package kaddy

import net.dv8tion.jda.core.utils.MiscUtil

interface IDMappedCollection<out ElementType> : MappedCollection<Long, ElementType> {

    /**
     * Returns a member of this collection that is mapped to the given id.
     *
     * @param id The id that represents a possible member of this collection.
     * @return The element mapped to the given id if the id exists, otherwise null.
     */
    operator fun get(id: CharSequence): ElementType? {
        return get(MiscUtil.parseSnowflake(id.toString()))
    }
}