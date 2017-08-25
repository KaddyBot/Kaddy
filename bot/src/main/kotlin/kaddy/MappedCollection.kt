package kaddy

/**
 * A special collection that maps all of its elements to a key of some type.
 */
interface MappedCollection<in KeyType, out ElementType> : Collection<ElementType> {

    /**
     * Returns a member of this collection that is mapped to the given key (or id).
     *
     * @param key The key that represents a possible member of this collection.
     * @return The element mapped to the given key if the key exists, otherwise null.
     */
    operator fun get(key: KeyType): ElementType?
}