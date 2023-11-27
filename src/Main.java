import java.util.Scanner;

public class Main {   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RPGCharacter player1 = RPGCharacter.createFirstCharacter(scanner);
        RPGCharacter player2 = RPGCharacter.createSecondCharacter(scanner);

        // Player 1 attacks Player 2
        player1.attack(player2);
        // player2.attack(player1);

        System.out.println("Player 1 HP: " + player1.currentHp);
        System.out.println("Player 2 HP: " + player2.currentHp);
    }
}


class Sword {
    private String name;
    private int level;

    public Sword(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // Assuming you have a method to calculate damage based on the sword's level
    public int calculateDamage() {
        // Implement based on your game logic
        return level * 10;
    }
}

class Shield {
    private String name;
    private int level;

    public Shield(String name, int level) {
        this.name = name;
        this.level = level;
    }

    // Assuming you have a method to calculate protection based on the shield's level
    public int calculateProtection() {
        // Implement based on your game logic
        return level * 5;
    }
}

class RPGCharacter {
    private String name;
    private int level;
    private int maxHp;
    public int currentHp;
    private Sword equippedSword;
    private Shield equippedShield;

    private RPGCharacter(String name, int level, int maxHp) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp * level;
        this.currentHp = this.maxHp;
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
        scanner.nextLine();

        RPGCharacter character = new RPGCharacter(name, level, maxHp);
        character.equipSword(scanner);
        character.equipShield(scanner);

        return character;
    }
    

    public static RPGCharacter createSecondCharacter(Scanner scanner) {
        System.out.println(" ");
        System.out.print("Name your second character: ");
        String name = scanner.nextLine();
        System.out.print("Second character's level: ");
        int level = scanner.nextInt();
        System.out.print("Second character's maxhp: ");
        int maxHp = scanner.nextInt();
        scanner.nextLine();

        RPGCharacter character = new RPGCharacter(name, level, maxHp);
        character.equipSword(scanner);
        character.equipShield(scanner);

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
    }

    private void equipShield(Scanner scanner) {
        System.out.print("Enter shield name: ");
        String shieldName = scanner.nextLine();
        System.out.print("Enter shield level: ");
        int shieldLevel = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Shield shield = new Shield(shieldName, shieldLevel);
        this.equippedShield = shield;
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
}