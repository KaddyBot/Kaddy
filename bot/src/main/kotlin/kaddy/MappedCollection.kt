package kaddy

interface MappedCollection<in KeyType, out ElementType> : Collection<ElementType> {

    /**
     * Returns a member of this collection that is mapped to the given key.
     *
     * @param key The key that represents a possible member of this collection.
     * @return The element mapped to the given key if the key exists, otherwise null.
     */
    operator fun get(key: KeyType): ElementType?
}