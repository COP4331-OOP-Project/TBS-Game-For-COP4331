package game.commands;

import java.util.*;

/**
 * A queue object to hold commands for entities.
 */
public class CommandQueue implements Queue
{
    private class Node
    {
        public Command command;
        public Node next;

        public Node(Command comm, Node n) {
            this.command = comm;
            this.next = n;
        }
    }

    private Node head = null;
    private Node tail = null;

    public CommandQueue(){}

    public boolean add(Command comm) {
        Node cNode = new Node(comm, null);

        if (isEmpty()) { head = cNode; }
        else { tail.next = cNode; }

        tail = cNode;
        return true;
    }

    public Command element() {
        if (this.isEmpty) { throw new NoSuchElementException(); }
        else { return head; }
    }

    public boolean offer(Command comm) {
        Node cNode = new Node(comm, null);

        if (isEmpty()) { head = cNode; }
        else { tail.next = cNode; }

        tail = cNode;
        return true;
    }

    public Command peek() {
        return head.command;
    }

    public Command poll() {
        if (isEmpty()) { return null; }

        else {
            Command comm = head.command;
            if (tail == head) { tail = null; }

            head = head.next;
            return comm;
        }
    }

    public Command remove() {
        if (isEmpty()) { throw new NoSuchElementException(); }

        else {
            Command comm = head.command;
            if (tail == head) { tail = null; }

            head = head.next;
            return comm;
        }
    }

    public int size() {
        int count = 0;
        for (Node node = head; node != null; node = node.next) {
            count++;
        }
        return count;
    }

    public boolean isEmpty() { return head == null; }

}