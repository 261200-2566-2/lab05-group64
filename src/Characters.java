public class Characters {
    protected int level;
    protected int maxHP;
    protected int maxMana;
    protected int currentHP;
    protected int currentMana;
    protected int ATK;
    protected int DEF;
    protected int Speed;
    protected Sword equippedSword;
    protected Shield equippedShield;
    protected Accessory equippedRing;
    protected Accessory equippedNecklace;
    protected Accessory equippedBoots;

    public Characters(int level) {
        this.level = level;
        this.maxHP = level * 10;
        this.maxMana = level * 5;
        this.currentHP = maxHP;
        this.currentMana = maxMana;
        this.ATK = level;
        this.DEF = level;
        this.Speed = 100;
    }

    public static class Warrior extends Characters {
        public Warrior(int level) {
            super(level);
            setCharacterTypeAttributes();
        }

        protected void setCharacterTypeAttributes() {
            this.ATK = 3 * level;
            this.maxHP = 8 * level;
            this.currentHP = maxHP;
            this.DEF = 8 * level;
            this.maxMana = 5 * level;
            this.currentMana = maxMana;
            this.Speed = 80;
        }
        public String getCharacterInfo(){
            return super.getCharacterInfo() + "\nType: Waarior";
        }
    }

    public static class Mage extends Characters {
        public Mage(int level) {
            super(level);
            setCharacterTypeAttributes();
        }

        protected void setCharacterTypeAttributes() {
            this.ATK = 5 * level;
            this.maxHP = 5 * level;
            this.currentHP = maxHP;
            this.DEF = 3 * level;
            this.maxMana = 8 * level;
            this.currentMana = maxMana;
            this.Speed = 100;
        }

        public String getCharacterInfo() {
            return super.getCharacterInfo() + "\nType: Mage";
        }
    }

    public static class Assassin extends Characters {
        public Assassin(int level) {
            super(level);
            setCharacterTypeAttributes();
        }

        protected void setCharacterTypeAttributes() {
            this.ATK = 8 * level;
            this.maxHP = 5 * level;
            this.currentHP = maxHP;
            this.DEF = 3 * level;
            this.maxMana = 5 * level;
            this.currentMana = maxMana;
            this.Speed = 120;
        }

        public String getCharacterInfo() {
            return super.getCharacterInfo() + "\nType: Assassin";
        }
    }


    protected int getLevel() {
        return level;
    }

    protected int getMaxHP() {
        return maxHP;
    }

    protected int getMaxMana() {
        return maxMana;
    }

    protected int getCurrentHP() {
        return currentHP;
    }

    protected int getCurrentMana() {
        return currentMana;
    }

    protected int getATK() {
        return ATK;
    }

    protected int getDEF() {
        return DEF;
    }

    protected int getSpeed() {
        return Speed;
    }

    protected Sword getEquippedSword() {
        return equippedSword;
    }

    public void equippedSword(Sword sword) {
        equippedSword = sword;
        ATK += sword.getDamage();
        DEF += sword.getDefense();
        Speed -= sword.getWeight();
    }

    protected Shield getEquippedShield() {
        return equippedShield;
    }

    public void equippedShield(Shield shield) {
        equippedShield = shield;
        ATK += shield.getDamage();
        DEF += shield.getDefend();
        Speed -= shield.getWeight();
    }

    public void equipRing(Accessory.Ring ring){
        equippedRing = ring;
        applyAccessoryModifiers(ring);
    }

    public void equipNecklace(Accessory.Necklace necklace) {
        equippedNecklace = necklace;
        applyAccessoryModifiers(necklace);
    }

    public void equipBoots(Accessory.Boots boots) {
        equippedBoots = boots;
        applyAccessoryModifiers(boots);
    }

    private void applyAccessoryModifiers(Accessory accessory){
        ATK += (int) (ATK * accessory.getATKModifier());
        DEF += (int) (DEF * accessory.getDEFModifier());
        maxHP += (int) (maxHP * accessory.getHpModifier());
        maxMana += (int) (maxMana * accessory.getManaModifier());
        Speed += (int) accessory.getSpeedModifier();
    }

    private void removeAccessoryModifiers(Accessory accessory) {
        ATK -= (int) (ATK * accessory.getATKModifier());
        DEF -= (int) (DEF * accessory.getDEFModifier());
        maxHP -= (int) (maxHP * accessory.getHpModifier());
        maxMana -= (int) (maxMana * accessory.getManaModifier());
        Speed -= (int) accessory.getSpeedModifier();
    }

    public void unequipRing() {
        if (equippedRing != null) {
            removeAccessoryModifiers(equippedRing);
            equippedRing = null;
        }
    }

    public void unequipNecklace() {
        if (equippedNecklace != null) {
            removeAccessoryModifiers(equippedNecklace);
            equippedNecklace = null;
        }
    }

    public void unequipBoots() {
        if (equippedBoots != null) {
            removeAccessoryModifiers(equippedBoots);
            equippedBoots = null;
        }
    }

    public void attack(Characters target) {
        int playerATK = ATK;
        int damage = 0;
        int defense = DEF;

        if (equippedSword != null) {
            playerATK += equippedSword.getDamage();
        } else if (equippedShield != null) {
            playerATK += equippedShield.getDamage();
        }

        if (playerATK > defense) {
            damage = playerATK - defense;
        }

        currentHP -= damage;
        reduceMana(20);
        target.takeDamage(Math.max(0, damage - defense));
    }

    private void reduceMana(int amount) {
        currentMana -= 20;
        if (currentMana <= 0) {
            currentMana = 0;
            System.out.println("Mana: " + currentMana);
        }
    }

    public void takeDamage(int damage) {
        int baseDefense = level;
        int effectiveDefense;

        if (equippedShield != null) {
            effectiveDefense = equippedShield.getDefend() + (baseDefense);
        } else {
            effectiveDefense = baseDefense;
        }

        damage = Math.max(0, damage - effectiveDefense);

        currentHP -= damage;

        if (currentHP <= 0) {
            System.out.println("YOU ARE DEAD!!!");
        } else {
            System.out.println("HP: " + currentHP + "/" + maxHP + "  Mana: " + currentMana + "/" + maxMana);
        }
    }

    public String getCharacterInfo() {
        return String.format("\nLevel: %d\nHP: %d/%d\nMana: %d/%d\nATK: %d\nDEF: %d\nSpeed: %d",
                level, currentHP, maxHP, currentMana, maxMana, ATK, DEF, Speed);
    }
}
