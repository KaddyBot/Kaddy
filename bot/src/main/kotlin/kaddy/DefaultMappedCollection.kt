package kaddy

internal class DefaultMappedCollection<in KeyType, out ElementType>(getCollection: () -> Collection<ElementType>,
                                                           private val getByKey: (KeyType) -> ElementType?)
    : MappedCollection<KeyType, ElementType>, Collection<ElementType> by getCollection() {

    override fun get(key: KeyType): ElementType? = getByKey(key)
}