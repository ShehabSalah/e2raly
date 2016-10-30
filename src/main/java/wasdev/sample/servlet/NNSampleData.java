package wasdev.sample.servlet;

/**
  * Java Neural Network Example
  * Handwriting Recognition
  * by Jeff Heaton (http://www.jeffheaton.com) 1-2002
  * -------------------------------------------------
  * A class that is used to store a downsampled character.
  *
  * @author Jeff Heaton (http://www.jeffheaton.com)
  * @version 1.0
  */
public class NNSampleData implements Cloneable {

/**
  * The downsampled data as a grid of booleans.
  */
   protected double grid[];

/**
  * The letter.
  */
   protected String letter;

/**
  * The constructor
  *
  * @param x What letter this is
  * @param width The width
  * @param height The height
  */
   public NNSampleData(String letter,int length)
   {
     grid = new double[length];
     this.letter = letter;
   }

/**
  * Set one pixel of sample data.
  *
  * @param x The x coordinate
  * @param y The y coordinate
  * @param c The value to set
  */
   public void setData(int index,double value)
   {
     grid[index]=value;
   }

/**
  * Get a pixel from the sample.
  *
  * @param x The x coordinate
  * @param y The y coordinate
  * @return The requested pixel
  */
   public double getData(int index)
   {
     return grid[index];
   }


/**
  * Get the height of the down sampled image.
  *
  * @return The height of the downsampled image.
  */
   public int getLength()
   {
     return grid.length;
   }


/**
  * Get the letter that this sample represents.
  *
  * @return The letter that this sample represents.
  */
   public String getLetter()
   {
     return letter;
   }

/**
  * Set the letter that this sample represents.
  *
  * @param letter The letter that this sample represents.
  */
   public void setLetter(String letter)
   {
     this.letter = letter;
   }
/**
  * Compare this sample to another, used for sorting.
  *
  * @param o The object being compared against.
  * @return Same as String.compareTo
  */

  // public int compareTo(Object o)
   {
     //SampleData obj = (SampleData)o;
   //  if ( this.getLetter()>obj.getLetter() )
     //  return 1;
     //else
      // return -1;
   }

/**
  * Convert this sample to a string.
  *
  * @return Just returns the letter that this sample is assigned to.
  */
   public String toString()
   {
     return ""+letter;
   }


/**
  * Create a copy of this sample
  *
  * @return A copy of this sample
  */
   public Object clone()

   {

     NNSampleData obj = new NNSampleData(letter,getLength());
       for ( int x=0;x<getLength();x++ )
         obj.setData(x,getData(x));
     return obj;
   }

}