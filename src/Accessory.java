public class Accessory {
    protected String name;
    protected double ATKModifier;
    protected double DEFModifier;
    protected double ManaModifier;
    protected double HpModifier;
    protected double SpeedModifier;

    public Accessory(String name, double ATKModifier, double DEFModifier, double ManaModifier, double HpModifier, double SpeedModifier){
        this.name = name;
        this.ATKModifier = ATKModifier;
        this.DEFModifier =DEFModifier;
        this.ManaModifier = ManaModifier;
        this.HpModifier = HpModifier;
        this.SpeedModifier = SpeedModifier;
    }

    public String getName() {
        return name;
    }

    public double getATKModifier() {
        return ATKModifier;
    }

    public double getDEFModifier() {
        return DEFModifier;
    }

    public double getHpModifier() {
        return HpModifier;
    }

    public double getManaModifier() {
        return ManaModifier;
    }

    public double getSpeedModifier() {
        return SpeedModifier;
    }

    public static class Ring extends Accessory{
        public Ring(){
            super("Ring",0.2 ,0.1,-0.05,0,0);
        }
    }

    public static class Necklace extends Accessory{
        public Necklace(){
            super("Necklace",0,0.1,0,0.2,-20);
        }
    }

    public static class Boots extends Accessory{
        public Boots(){
            super("Boots",0,0.1,0,-0.05,20);
        }
    }

}
