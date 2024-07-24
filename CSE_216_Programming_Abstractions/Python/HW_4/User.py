class User:
    def __init__(self,name, email, *friends):

        self.friends = list(friends)
        self.email = email
        self.name = name
    def __str__(self):
        ret = 100*"-" + "\n" + "Name: " + self.name + "\nEmail: " + self.email +"\n"
        ret += "Friends: \n"
        for friend in self.friends:
            ret += f"Name: {friend.name:40} Email: {friend.email} \n"
        ret +=100*"-"
        return ret
    def hasFriend(self, email):
        for friend in self.friends:
            if (email.lower() == friend.email):
                return True
        return False
    
