interface TerrainEffect {
    int getMovementModifier();
    boolean allowsMovement();
    String getTerrainDescription();
}