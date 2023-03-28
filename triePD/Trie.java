package triePD;

import java.util.ArrayList;
import java.util.TreeMap;

public class Trie {
	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public Boolean isEmpty() {
		return root.children.size() == 0;
	}
	
	public void addWord(String word, int index) {		
		Node node = root;
		String wordPart = word.toLowerCase() + "$";
		
		while(wordPart.length() > 0) {
			Node child = node.getChild(wordPart.charAt(0));
			
			if(child == null) {
				child = node.addChild(wordPart.charAt(0), false);
				wordPart = wordPart.substring(1);
				
				if(wordPart.length() == 0) {
					child.addIndex(index);
					child.setIsEnd(true);
				}
				
				node = child;
			}
			else if(!child.getIsEnd() && wordPart.length() != 1)	{
				node = child;
				wordPart = wordPart.substring(1);
			}
			else if(child.getIsEnd() && wordPart.length() == 1)	{
				child.addIndex(index);
				wordPart = "";
			}
		}
	}
	
	public ArrayList<Integer> indexesForWord(String word) {
		Node node = root;
		String wordPart = word + "$";
		
		while (wordPart.length() > 0) {
			Node child = node.getChild(wordPart.charAt(0));
			
			if(child == null)
				return null;
			
			node = child;
			wordPart = wordPart.substring(1);
		}
		return node.getIndex();
	}
	
	private class Node {
		private TreeMap<Character, Node> children;
		private char element;
		private Boolean isEnd;
		private ArrayList<Integer> index;
		
		public Node() {
			setChildren(new TreeMap<Character, Node>());
			setIndex(new ArrayList<Integer>());
		}
		
		public Node(char element, Boolean isEnd) {
			this();
			this.setElement(element);
			this.setIsEnd(isEnd);
		}
		
		public Node addChild(char element, Boolean isEnd) {
			Node node = new Node(element, isEnd);
			children.put(element, node);
			return node;
		}
		
		public void addIndex(int index) {
			this.index.add(index);
		}
		
		public Node getChild(char element) {
			return children.get(element);
		}

		public void setChildren(TreeMap<Character, Node> children) {
			this.children = children;
		}

		public void setElement(char element) {
			this.element = element;
		}

		public Boolean getIsEnd() {
			return isEnd;
		}

		public void setIsEnd(Boolean isEnd) {
			this.isEnd = isEnd;
		}

		public ArrayList<Integer> getIndex() {
			return index;
		}

		public void setIndex(ArrayList<Integer> index) {
			this.index = index;
		}
	}
}