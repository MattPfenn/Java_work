package ch.hearc.list;

public class List<C> {

	private class Node {
		public C obj;
		public Node next;
		public Node prev;
		Node (C o) { obj = o; next = null; prev = null; }
	}

	Node tail;
	Node head;
	int size;

	public List() {
		tail = head = null;
		size = 0;
	}

	public void addTail(C o) {
		Node newTail = new Node(o);
		if (size == 0) {
			head = newTail;
		} else {
			tail.next = newTail;
			newTail.prev = tail;
		}
		tail = newTail;
		size ++;
	}

	public void addHead(C o) {
		Node newHead = new Node(o);
		if (size == 0) {
			tail = newHead;
		} else {
			head.prev = newHead;
			newHead.next = head;
		}
		head = newHead;
		size ++;
	}

	public C getHead(boolean pop) {
		if (size == 0) return null;
		Node h = head;
		if (pop) {
			head = head.next;
			size --;
			if (size == 0)
				tail = null;
			else
				head.prev = null;
		}
		return h.obj;
	}

	public C getTail(boolean pop) {
		if (size == 0) return null;
		Node t = tail;
		if (pop) {
			tail = tail.prev;
			size --;
			if (size == 0)
				head = null;
			else
				tail.next = null;
		}
		return t.obj;
	}

}
