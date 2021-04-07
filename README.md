# MapleQuest

## A simple text-based RPG

My project will consist of an application that runs a simple text-based RPG game. The game is relatively simple, and
can be played by anyone, even without prior knowledge of major RPG elements. The motivation behind this project is that 
I've always had an interest in web-based RPGs throughout my childhood, and this exercise would be a great opportunity
to experiment and learn the underlying workings behind such a game.

Users will be able to experience the world of *Aurora*, and begin their hero adventures in the small town of *Henesys*.
From there, heroes will be able to explore new areas, battle a variety of monsters, and collect powerful weapons
that will help you continue your journey. 

## User Stories

In MapleQuest, users can expect the following features:

- As a user, I want to be able to view my hero's current inventory and status
- As a user, I want to be able to heal my hero from a vendor
- As a user, I want to be able to carry multiple weapons
- As a user, I want to purchase new weapons from a Weaponsmith
- As a user, I want to upgrade weapons from a Weaponsmith
- As a user, I want to be able dismantle weapons from the Weaponsmith
- As a user, I want to battle monsters.
- As a user, I want to be able to select between different weapons from my inventory to attack with.
- As a user, I want to be able to save the weapons I've gotten with my hero to file.
- As a user, I want to be able to reload with the weapons from my previously created hero from file.

## Phase 4: Task 2
Option 2: "Include a type hierarchy in your code other than the one that uses the Saveable interface introduced in 
Phase 2.  You must have more than one subclass and your subclasses must have distinct functionality.  They must 
therefore override at least one method inherited from a super type and override it in different ways in 
each of the subclasses."

Classes: Monster, Vampire, Ogre

Methods: performSpecialEffect()

## Phase 4: Task 3
If given more time, these would be the refactoring decisions:

- Make Monster class an abstract class with default behaviour, allowing generation of future subtypes of monsters
easily
- Similarly, make Weapon class possibly an abstract class with more default behaviour, allows generation of future
subtypes of weapons more easily
- Refactor MapleQuest to eliminate redundant code such that MapleQuestGUI can easily utilize methods that already call
methods from Hero, JSONReader, and JSONWriter classes
- Beyond that, design appears appropriate with minimal coupling, but refactoring can definitely be more present to
improve cohesion within classes such as MapleQuestGUI