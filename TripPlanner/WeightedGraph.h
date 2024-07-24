#pragma once

#include <map>
#include <cfloat>
using namespace std;

class WeightedEdge
{
private:
	wstring node1;
	wstring node2;
	float weight;

public:
	WeightedEdge(wstring initNode1, wstring initNode2, float initWeight)
	{
		this->node1 = initNode1;
		this->node2 = initNode2;
		this->weight = initWeight;
	}
	~WeightedEdge()
	{
		// NO POINTERS TO CLEAN UP
	}

	wstring getNode1() { return node1; }
	wstring getNode2() { return node2; }
	float getWeight() { return weight; }
};

template <typename T>
class WeightedGraph
{
private:
	std::map<wstring, T*> nodes;
	std::map<wstring, WeightedEdge*> edges;

public:
	WeightedGraph() {}

	void getKeys(vector<wstring>* keys)
	{
		for (const auto& keyPair : nodes)
		{
			wstring key = keyPair.first;
			keys->push_back(key);
		}
	}

	bool nodeExists(wstring testNode)
	{
		return this->nodes.count(testNode) > 0;
	}

	wstring getEdgeId(wstring node1, wstring node2)
	{
		return node1 + L"-" + node2;
	}

	void addNode(wstring nodeId, T *nodeData)
	{
		this->nodes[nodeId] = nodeData;
	}

	T* getNodeData(wstring nodeId)
	{
		return this->nodes[nodeId];
	}

	void addEdge(wstring node1, wstring node2, float weight)
	{
		wstring edgeId = this->getEdgeId(node1, node2);
		this->edges[edgeId] = new WeightedEdge(node1, node2, weight);
	}

	void removeEdge(wstring node1, wstring node2)
	{
		wstring edgeId = this->getEdgeId(node1, node2);
		WeightedEdge* edge = this->edges[edgeId];
	}

	bool hasNeighbor(wstring node1, wstring node2)
	{
		wstring edgeId = this->getEdgeId(node1, node2);
		return this->edges.find(edgeId) != this->edges.end();
	}

	float getNeighborWeight(wstring node1, wstring node2)
	{
		if (hasNeighbor(node1, node2))
		{
			wstring edgeId = this->getEdgeId(node1, node2);
			return this->edges[edgeId]->getWeight();
		}
		return 0.0f;
	}

	void findPath(vector<wstring> *path, wstring node1, wstring node2)
	{ // Greedy implementation
		if (nodeExists(node1) && nodeExists(node2))
		{
			vector<wstring> visited;
			visited.emplace_back(node1);
			path->emplace_back(node1);
			wstring currentNode = node1;
			while (currentNode != node2)
			{
				wstring minNode = L"empty";
				float minWeight = FLT_MAX;
				typename map<wstring, T*>::iterator neighborNode = nodes.begin();
				while (neighborNode != nodes.end())
				{	
					if(!hasNode(visited, neighborNode->first)){
						if (this->hasNeighbor(currentNode, neighborNode->first))
						{	if(neighborNode->first == node2){
								minNode = node2;
								break;
							}
							if ((minWeight > this->getNeighborWeight(currentNode, neighborNode->first)))
							{
								minWeight = getNeighborWeight(currentNode, neighborNode->first);
								minNode = neighborNode->first;
							}
							
						}
						else if (this->hasNeighbor(neighborNode->first,currentNode))
						{	if(neighborNode->first == node2){
								minNode = node2;
								break;
							}
							if ((minWeight > this->getNeighborWeight(neighborNode->first,currentNode)))
							{
								minWeight = getNeighborWeight(neighborNode->first,currentNode);
								minNode = neighborNode->first;
							}
						}
					}
					++neighborNode;
				}
				if (minNode != L"empty")
				{	
					path->emplace_back(minNode);
					visited.emplace_back(minNode);
					currentNode = minNode;
					minNode = L"empty";
				}
				else
				{
					path->pop_back();
					if(path->empty()){
						wcout<<"No Path Found" << endl;
						break;
					}
					currentNode = path->back();
				}
			}
		}
	}
	bool hasNode(vector<wstring> vec, wstring node)
	{
		vector<wstring>::iterator it = vec.begin();
		while (it != vec.end())
		{
			if (*it == node)
			{
				return true;
			}
			++it;
		}
		return false;
	}
};