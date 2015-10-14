package compiler.util;

public class LLNode<T>
{
    public LLNode()
    {
    }
    
    void setValue( T value )
    {
        this.value  = value;
    }
    
    void setPrevious( LLNode<T> node )
    {
        this.previous = node;
    }
    
    void setNext( LLNode<T> node )
    {
        this.next = node;
    }
    
    public T getValue()
    {
        return this.value;
    }
    
    public LLNode<T> getPrevious()
    {
        return this.previous;
    }

    public LLNode<T> getNext()
    {
        return this.next;
    }
    
    private T         value;
    private LLNode<T> previous;
    private LLNode<T> next;
}
