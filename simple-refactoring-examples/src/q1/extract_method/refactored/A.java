package q1.extract_method.refactored;

import java.util.List;

public class A {
   Node m1(List<Node> nodes, String p) 
   {
	   extractedMethod(nodes, p);
	   return null;
   }

   Edge m2(List<Edge> edgeList, String p) 
   {
     
	   extractedMethod(edgeList, p);
	   return null;
   }

   <T extends Graph> void extractedMethod(List<T> nodes, String p) 
   {
   	for (T node : nodes) {
			if (node.contains(p))
				System.out.println(node);
		}
   }
}
class Graph {
	String name;
   public boolean contains(String p)
   {
       return name.contains(p);
   }
}
class Node extends Graph 
{
   
}

class Edge extends Graph
{

}