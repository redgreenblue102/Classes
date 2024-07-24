from User import User
class FriendNetwork:
    def __init__(self):
        self.users = []
        self.edges = []
    
    def addUser(self, user):
        self.users.append(user)
    
    def addEdge(self, email1, email2):
        self.edges.append(Edge(email1,email2))

    def isEmpty(self):
        if len(self.users) ==0:
            return True
        return False
    def removeEdge(self,email1,email2):
        for edge in self.edges:
            if((edge.label == (email1.lower() +"-" + email2.lower())) or (edge.label == (email2.lower() +"-" + email1.lower()))):
                self.edges.remove(edge)
    def exists(self, email):
        for user in self.users:
            if(email.lower() == user.email):
                return True
        return False
    def edgeExists(self,email1,email2):
        for edge in self.edges:
            if(email1.lower() +"-" + email2.lower() == edge.label or email2.lower() +"-" + email1.lower() == edge.label ):
                return True
        return False
    def getUser(self,email):
        for user in self.users:
            if(email.lower() == user.email):
                return user
        return -1
            
    def makeFriends(self,email1, email2):
        self.getUser(email1).friends.append(self.getUser(email2))
        self.addEdge(email1,email2)
        self.getUser(email2).friends.append(self.getUser(email1))

    def unFriend(self, email1,email2):
        if (self.getUser(email1).hasFriend(email2)):
            self.getUser(email1).friends.remove(self.getUser(email2))
            self.getUser(email2).friends.remove(self.getUser(email1))
            self.removeEdge(email1,email2)
            return 0
        else:
            return -1
    def breadthSearch(self,start, target):
        visited = []
        queue = [start]
        hops = 0
        nextHopNodes = 0
        currentHopNodes = 1
        while(len(queue) != 0):
            currNode = queue.pop(0)
            currentHopNodes -= 1
            if currNode == target:
                return hops
            visited.append(currNode)
            for edge in self.edges:
                if(edge.edge1 == currNode):
                    if(visited.count(edge.edge2) == 0):
                        queue.append(edge.edge2)
                        nextHopNodes +=1
                elif(edge.edge2 == currNode):
                    if(visited.count(edge.edge1) == 0):
                        queue.append(edge.edge1)
                        nextHopNodes +=1
            if currentHopNodes == 0:
                currentHopNodes = nextHopNodes
                nextHopNodes = 0
                hops += 1
        return 0
    def breadthSearchForHops(self, start, requiredHops):
        visited = []
        queue = [start]
        hops = 0
        nextHopNodes = 0
        currentHopNodes = 1
        while(len(queue) != 0):
            currNode = queue.pop(0)
            currentHopNodes -= 1
            visited.append(currNode)
            for edge in self.edges:
                if(edge.edge1 == currNode):
                    if(visited.count(edge.edge2) == 0):
                        queue.append(edge.edge2)
                        nextHopNodes +=1
                elif(edge.edge2 == currNode):
                    if(visited.count(edge.edge1) == 0):
                        queue.append(edge.edge1)
                        nextHopNodes +=1
            if currentHopNodes == 0:
                currentHopNodes = nextHopNodes
                nextHopNodes = 0
                hops += 1
            if(hops == requiredHops):
                return queue
        return []
    def clear(self):
        self.users.clear()
        self.edges.clear()
    def __str__(self):
        ret = ""
        for user in self.users:
            ret += user.__str__() + "\n"
        return ret


class Edge:
    def __init__(self,edge1,edge2):
        self.edge1 = edge1.lower()
        self.edge2=edge2.lower()
        self.label = self.edge1+"-"+self.edge2