[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/bIZQaFcG)

1. How and Where to Store Data
   Player Data:
   Create a Player class that holds information about the player, including a list of RPG characters.
   Use a data structure like ArrayList<RPGCharacter> to store multiple characters for a player.
   Game Data:
   Consider creating a GameManager class that manages the overall game state.
   Store players and other game-related data in this manager class.

2. What Operations Are Needed
   Character Creation: Handled by createFirstCharacter and createSecondCharacter methods, taking input from the user.
   Equipping Weapons: Handled by the equipSword and equipShield methods, taking input from the user.
   Attack: Handled by the attack method in the RPGCharacter class.
   Damage is calculated based on the equipped sword's level.
   Take Damage: Handled by the takeDamage method in the RPGCharacter class.
   Damage is reduced based on the equipped shield's level.

3. How and Where to Compute Information
   Calculations:
   Implement methods for calculating damage and protection within the Sword and Shield classes.
   Consider having a centralized BattleManager or CombatCalculator class for handling combat-related calculations.
   Game Logic:
   Implement game logic methods in the GameManager class to handle actions like attacking, turn management, and resolving battles.
