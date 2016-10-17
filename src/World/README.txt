How the system works:
The Map show us where what is. It's read first.
Citys on the map start with an "-", followed by the identifier, which consists of 2 alphanumerical letters.

Cities are then read constructed into the world.


Quickguide Map:
0	- Ground
I 	- Barricade
-XX	- City
X	- You
E	- Enemy



Quickguide Structure City:
JSon-Object with following structure
{
  "name": "Tamariel", 				--> Name of the city
  "identifier": "TA", 				--> Identifier same as on the map
  "places": ["P1", "P2", "P3"]		--> Which places you will find in the city
}




Quickguide Places:
P1	- Postoffice
P2	- Market
P3	- Whorehouse 




Quickguide Structure Places:
JSon-Object with following structure
{
  "name": "International Postoffice", 				--> Name of the city
  "People": ["H1", "H2", "H3"]						--> Which places you will find in the city
}




Quickguide Humans:
H1	- Whore
H2	- Merchant
H3	- Quest-Giver
H4	- ???




Quickguide Structure Humans:
JSon-Object with following structure
{
  "name": "Anna",					 				--> Name of the human
  "Inventory": ["I1", "I2", "I3"],					--> Items in Inventory
  "wantItem": "I1",
  "givesItem": "I3",
  "quantity": 10
}




Quickguide Items:
I1	- Sword
I2	- Banana
I3	- Gold
I4	- ???



Quickguide Structure Humans:
JSon-Object with following structure
{
  "name": "Sword",					 				--> Name of the city
  "itemID": "I1"									--> ID of item
  "damageValue": 1,								--> Damage increase/decrease when in hand
  "protectionValue": "0"							--> Damage decrease/increase when worn
}