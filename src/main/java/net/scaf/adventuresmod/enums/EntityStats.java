package net.scaf.adventuresmod.enums;

public enum EntityStats {
    TEMPLE_GUARD(40, 5);

    final double health, attackDamage, followRange, movementSpeed;
    public static final double normalHealth = 20D, normalDamage = 1D, normalSpeed = .27000000417232513, normalFollowRange = 20D;

    EntityStats(double health, double attackDamage) {
        this.health = health;
        this.attackDamage = attackDamage;
        this.followRange = normalFollowRange;
        this.movementSpeed = normalSpeed;

    }

    public double getHealth() {return health;}
    public double getAttackDamage() {return attackDamage;}
    public double getFollowRange() {return followRange;}
    public double getMovementSpeed() {return movementSpeed;}
}
