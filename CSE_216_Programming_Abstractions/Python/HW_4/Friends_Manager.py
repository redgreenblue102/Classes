from User import User
from FriendNetwork import FriendNetwork

network = FriendNetwork()

def loadTestData():
    people = ["Ansley Perkins",
    "Audrey Lloyd",
    "Kaitlynn Casey",
    "Abbigail Whitaker",
    "Trevin Harrell",
    "Gael Weaver",
    "Bryanna Landry",
    "Rachael Santos",
    "Josh Delacruz",
    "Finn Richards",
    "Dorian Carson",
    "Tyson Little",
    "Eddie Sparks",
    "Rodrigo Decker",
    "Kathy Henderson",
    "Monica Rojas",
    "Laila Potter",
    "Dorian Safe",
    "Jorden Crane",
    "Jayda Giles",
    "Jordyn Fuentes",
    "Zaria Dalton",
    "Dorian Finney",
    "Jessica Castaneda",
    "Ty Cooper",
    "Harley Ali",
    "Gunnar Prince",
    "Tyler Griffith",
    "Josh Deca",
    "Immanuel Valenzuela"]
    Dordupe = 0
    #new lines show seperated groups of friends
    friendsOfPeople =  {"Ansley Perkins" : [],
                        "Audrey Lloyd" : ["Ansley Perkins"],
                        "Kaitlynn Casey" : ["Ansley Perkins", "Audrey Lloyd"],
                        "Abbigail Whitaker" : ["Ansley Perkins", "Audrey Lloyd", "Kaitlynn Casey"],
                        "Trevin Harrell" : ["Ansley Perkins"],
                        "Gael Weaver" : ["Trevin Harrell"],
                        "Bryanna Landry" : ["Gael Weaver"],
                        "Rachael Santos" : ["Bryanna Landry"],
                        "Josh Delacruz" : ["Trevin Harrell"],
                        "Finn Richards": ["Trevin Harrell"],
                        "Dorian Carson" : ["Josh Delacruz"],
                        "Tyson Little" : ["Rachael Santos"],
                        "Eddie Sparks": ["Tyson Little"],
                        "Jordyn Fuentes": ["Eddie Sparks"],
                        "Zaria Dalton": ["Ansley Perkins"],
                        "Dorian Finney": ["Jordyn Fuentes"],
                        "Jessica Castaneda":["Zaria Dalton"],
                        "Ty Cooper": ["Jessica Castaneda"],
                        "Harley Ali": ["Rachael Santos"],
                        "Gunnar Prince": ["Finn Richards"] ,
                        "Tyler Griffith" : ["Harley Ali"],
                        "Josh Deca": ["Ty Cooper"] ,
                        "Immanuel Valenzuela": ["Josh Deca"], 


                        "Rodrigo Decker" : [],


                        "Kathy Henderson": [],
                        "Monica Rojas" : ["Kathy Henderson"],
                        "Laila Potter": ["Kathy Henderson"],
                        "Dorian Safe": ["Laila Potter"],
                        "Jorden Crane": ["Monica Rojas"],
                        "Jayda Giles": ["Dorian Safe"]}
    
    for person in people:
        temp=person.split()
        friends = friendsOfPeople[person]
        emailsFriends = []
        for friend in friends:
            split = friend.split()
            emailsFriends.append(split[0].lower()+"@" +split[1].lower())
        
        addPerson(person, temp[0].lower()+"@" +temp[1].lower(),*emailsFriends)
    

def addPerson(name, email, *friends):
    if (network.exists(email) == False):
        if (len(friends) == 0):
            network.addUser(User(name, email))
        else:
            for friend in friends:
                if (network.exists(friend) == False):
                    return friend
            toAdd=network.addUser(User(name, email))
            for friend in friends:
                addFriend(email, friend)
    else:
        return "-1"
    return "0"

def displayAll():
     print(network)
def addFriend(email1,email2):
    if network.exists(email1):
        if network.exists(email2):
            if network.edgeExists(email1,email2):
                print("Already Friends")
            else:
                network.makeFriends(email1,email2)
        else: 
            print(email2 + " Does Not Exist")
    else:
        print(email1 + " Does Not Exist")
def printMenu():
    print("Menu\nA: Add Person\nB: Are They Friends\n"+
            "C: Make Friends\nD: Unfriend\nE: View Friends\n"+
            "F: View Connections\nG: Count Degrees\nH: Display All Users\n" +
            "I: Clear All Users\nJ: Load Test Data\nX: Quit Application")

print("Welcome to the Friends Manager!")



cont = True
while(cont):
    printMenu()
    x =input()
    match x.upper():
        case "A":
            print("Input Name")
            name = input()
            print("Input Email, Case insensitive, requires one @. WhiteSpaces and commas are not registered")
            email = input().replace(" ","").lower()
            if(tuple(email).count("@") == 1 and tuple(email).count(",") == 0):
                print("Optional Input friend emails seperated by commas. Case Insensitive")
                friends = input().replace(" ","").lower().split(",")
                if(friends == [""]):
                    friends = []
                res =addPerson(name, email, *friends)
                if(res == "-1"):
                    print("User email already exists")
                elif(res == "0"):
                    print("User Added")
                else:
                    print("The friend you are trying to add, " + res + " does not exist. User Not Added")
                
            else:
                print("Invalid email")

        case "B":
            print("First persons email")
            email1 = input().lower().strip()
            if(network.exists(email1)):
                print("Second persons email")
                email2 = input().lower().strip()
                if network.exists(email2):
                    if network.edgeExists(email1,email2):
                        print(email1 + " is friends with " + email2)
                    else:
                        print(email1 + " and " + email2 + " are not friends")
                else:
                    print("User does not exist")
            else:
                print("User does not exist")

        case "C":
            print("First persons email")
            email1 = input().lower().strip()
            if(network.exists(email1)):
                print("Second persons email")
                email2 = input().lower().strip()
                if network.exists(email2):
                    if network.edgeExists(email1,email2):
                        print(email1 + " is already friends with " + email2)
                    else:
                        addFriend(email1,email2)
                        print(email1 + " and " + email2 + " have become friends")
                else:
                    print("User does not exist")
            else:
                print("User does not exist")
        case "D":
            print("First persons email")
            email1 = input().lower().strip()
            if(network.exists(email1)):
                print("Second persons email")
                email2 = input().lower().strip()
                if network.exists(email2):
                    if network.unFriend(email1,email2) == 0:
                        print(email1 + " has been unfriended from " + email2)
                    else:
                        print(email1 + " and " + email2 + " are already not friends")
                else:
                    print("User does not exist")
            else:
                print("User does not exist")
        case "E":
            print("Input Email of User")
            email = input().lower().strip()
            user=network.getUser(email)
            if(user == -1):
                print("User does not exist")
            else:
                print(user)

        case "F":
            print("Input email of origin")
            email = input().lower().strip()
            if(network.exists(email)):
                print("Input hops amount as integer")
                try:
                    hops = int(input())
                    connections = network.breadthSearchForHops(email,hops)
                    if len(connections) == 0:
                        print(f"No friends {hops} hops away")
                    else:
                        for friend in connections:
                            print(friend)
                except ValueError:
                    print("Invalid input")
                
            else:
                print("email does not exist")

            
        case "G":
            print("First persons email")
            email1 = input().lower().strip()
            if(network.exists(email1)):
                print("Second persons email")
                email2 = input().lower().strip()
                if network.exists(email2):
                    hops = network.breadthSearch(email1,email2) 
                    if hops == 0:
                        print(email1 + " is not connected to " + email2)
                    else:
                        print(f"There is {hops} hops between {email1} and {email2}")
                else:
                    print("User does not exist")
            else:
                print("User does not exist")
        case "H":
            displayAll()
        case "I":
            network.clear()
        case "J":
            loadTestData()
            print("Loaded")
        case "X":
            cont = False
        case _:
            print("Invalid Input")
