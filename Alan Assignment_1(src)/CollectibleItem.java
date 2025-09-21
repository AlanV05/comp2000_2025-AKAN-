interface CollectibleItem<T extends Actor> {
    boolean canBeCollectedBy(T actor);
    void onCollected(T actor);
    String getCollectionMessage(T actor);
}