import java.util.Scanner;

public class Main {   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RPGCharacter player1 = RPGCharacter.createFirstCharacter(scanner);
        RPGCharacter player2 = RPGCharacter.createSecondCharacter(scanner);

        // Player 1 attacks Player 2
        player1.attack(player2);
        // Player 2 attacks Player 1
        player2.attack(player1);

        System.out.println("Player 1 HP: " + player1.getCurrentHp());
        System.out.println("Player 1 Mana: " + player1.getMana());
        System.out.println("Player 1 Run Speed: " + player1.getRunSpeed());

        System.out.println("Player 2 HP: " + player2.getCurrentHp());
        System.out.println("Player 2 Mana: " + player2.getMana());
        System.out.println("Player 2 Run Speed: " + player2.getRunSpeed());
    }
}

class Sword {
    private String name;
    private int level;
    private int effectOnRunSpeed;

    public Sword(String name, int level) {
        this.name = name;
        this.level = level;
        this.effectOnRunSpeed = -level; 
    }

    public int calculateDamage() {
        double baseDamage = 10.0;
        return (int) (baseDamage * (1 + 0.1 * level));
    }

    public int calculateRunSpeedDecrease() {
        double baseRunSpeedDecrease = 0.1;
        return (int) (baseRunSpeedDecrease + 0.04 * level);
    }

    public int getEffectOnRunSpeed() {
        return effectOnRunSpeed;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}

class Shield {
    private String name;
    private int level;
    private int effectOnRunSpeed;

    public Shield(String name, int level) {
        this.name = name;
        this.level = level;
        this.effectOnRunSpeed = -level; // Assuming a negative effect on run speed
    }

    public int calculateProtection() {
        double baseDefense = 5.0;
        return (int) (baseDefense * (1 + 0.05 * level));
    }

    public int calculateRunSpeedDecrease() {
        double baseRunSpeedDecrease = 0.1;
        return (int) (baseRunSpeedDecrease + 0.08 * level);
    }

    public int getEffectOnRunSpeed() {
        return effectOnRunSpeed;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}

class RPGCharacter {
    private String name;
    private int level;
    private int maxHp;
    private int currentHp;
    private int maxMana;
    private int mana;
    private int baseRunSpeed = 10; // Assuming a base run speed
    private int runSpeed;
    private int runSpeedDecrease;
    private Sword equippedSword;
    private Shield equippedShield;

    private RPGCharacter(String name, int level, int maxHp, int maxMana) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp * level;
        this.maxMana = maxMana * level;
        this.currentHp = this.maxHp;
        this.mana = this.maxMana;
        this.runSpeed = baseRunSpeed;
        this.runSpeedDecrease = 0;
        this.equippedSword = null;
        this.equippedShield = null;
    }

    public static RPGCharacter createFirstCharacter(Scanner scanner) {
        System.out.print("Name your first character: ");
        String name = scanner.nextLine();
        System.out.print("First character's level: ");
        int level = scanner.nextInt();
        System.out.print("First character's maxhp: ");
        int maxHp = scanner.nextInt();
        System.out.print("First character's max mana: ");
        int maxMana = scanner.nextInt();
        scanner.nextLine();

        RPGCharacter character = new RPGCharacter(name, level, maxHp, maxMana);

        System.out.print("Do you want to equip a sword? (yes/no): ");
        String swordChoice = scanner.nextLine();
        if (swordChoice.equalsIgnoreCase("yes")) {
            character.equipSword(scanner);
        }

        System.out.print("Do you want to equip a shield? (yes/no): ");
        String shieldChoice = scanner.nextLine();
        if (shieldChoice.equalsIgnoreCase("yes")) {
            character.equipShield(scanner);
        }

        return character;
    }

    public static RPGCharacter createSecondCharacter(Scanner scanner) {
        System.out.print("Name your second character: ");
        String name = scanner.nextLine();
        System.out.print("Second character's level: ");
        int level = scanner.nextInt();
        System.out.print("Second character's maxhp: ");
        int maxHp = scanner.nextInt();
        System.out.print("Second character's max mana: ");
        int maxMana = scanner.nextInt();
        scanner.nextLine();

        RPGCharacter character = new RPGCharacter(name, level, maxHp, maxMana);

        System.out.print("Do you want to equip a sword? (yes/no): ");
        String swordChoice = scanner.nextLine();
        if (swordChoice.equalsIgnoreCase("yes")) {
            character.equipSword(scanner);
        }

        System.out.print("Do you want to equip a shield? (yes/no): ");
        String shieldChoice = scanner.nextLine();
        if (shieldChoice.equalsIgnoreCase("yes")) {
            character.equipShield(scanner);
        }

        return character;
    }

    private void equipSword(Scanner scanner) {
        System.out.print("Enter sword name: ");
        String swordName = scanner.nextLine();
        System.out.print("Enter sword level: ");
        int swordLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Sword sword = new Sword(swordName, swordLevel);
        this.equippedSword = sword;
        this.runSpeedDecrease += sword.calculateRunSpeedDecrease();
    }

    private void equipShield(Scanner scanner) {
        System.out.print("Enter shield name: ");
        String shieldName = scanner.nextLine();
        System.out.print("Enter shield level: ");
        int shieldLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Shield shield = new Shield(shieldName, shieldLevel);
        this.equippedShield = shield;
        this.runSpeedDecrease += shield.calculateRunSpeedDecrease();
    }

    public void attack(RPGCharacter target) {
        if (this.equippedSword != null) {
            int damageDealt = this.equippedSword.calculateDamage();
            target.takeDamage(damageDealt);
        }
    }

    public void takeDamage(int damage) {
        if (this.equippedShield != null) {
            damage -= this.equippedShield.calculateProtection();
            if (damage < 0) {
                damage = 0;
            }
        }
        this.currentHp -= damage;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMana() {
        return mana;
    }

    public int getRunSpeed() {
        return runSpeed - runSpeedDecrease;
    }

    public void levelUp() {
        this.level++;
        this.maxHp = 100 + 10 * level;
        this.maxMana = 50 + 2 * level;
        this.currentHp = this.maxHp;
        this.mana = this.maxMana;

        this.runSpeed = (int) (baseRunSpeed * (0.1 + 0.03 * level));
        if (this.runSpeed < 0) {
            this.runSpeed = 0;
        }
    }
}
