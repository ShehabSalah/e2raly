package wasdev.sample.servlet;

import java.util.Arrays;
import java.util.Random;

/**
  * Java Neural Network Example
  * Handwriting Recognition
  * by Jeff Heaton (http://www.jeffheaton.com) 1-2002
  * -------------------------------------------------
  *
  * This class implements a basic Kohonen Neural Network.
  *
  * @author Jeff Heaton (http://www.jeffheaton.com)
  * @version 1.0
  */


public class KohonenNetwork  {
	/**
	 * The value to consider a neuron on
	 */
	public final double NEURON_ON=0.9;

	/**
	 * The value to consider a neuron off
	 */
	public final double NEURON_OFF=0.1;

	/**
	 * Output neuron activations
	 */
	protected double output[];

	/**
	 * Mean square error of the network
	 */
	protected double totalError;

	/**
	 * Number of input neurons
	 */
	protected int inputNeuronCount;

	/**
	 * Number of output neurons
	 */
	protected int outputNeuronCount;

	/**
	 * Random number generator
	 */
	protected Random random = new Random(System.currentTimeMillis());

	/**
	 * The weights of the output neurons base on the input from the
     * input neurons.
     */
	double outputWeights[][];

	/**
     * The learning method.
     */
	protected int learnMethod = 1;

	/**
     * The learning rate.
     */
	protected double learnRate = 0.01;

	/**
     * Abort if error is beyond this
     */
	protected double quitError = 0.00001;

	/**
     * How many retries before quit.
     */
	protected int retries = 300000000;

	/**
     * Reduction factor.
     */
	protected double reduction = .99;

	/**
     * Set to true to abort learning.
     */
	public boolean halt = false;

	/**
     * The training set.
     */
	protected NNTrainingSet train;

	int numberofVote;
	
	/**
     * The constructor.
     *
     * @param inputCount Number of input neurons
     * @param outputCount Number of output neurons
     * @param owner The owner object, for updates.
     */
	public KohonenNetwork(int inputCount,int outputCount,int n) {
		totalError = 1.0 ;
		this.inputNeuronCount = inputCount;
		this.outputNeuronCount = outputCount;
		this.outputWeights = new double[outputNeuronCount][inputNeuronCount+1];
		this.output = new double[outputNeuronCount];
		this.numberofVote=n;
	}

	/**
     * Set the training set to use.
     *
     * @param set The training set to use.
     */
	public void setTrainingSet(NNTrainingSet set) {
		train = set;
	}

	/**
	 * Copy the weights from this network to another.
     *
     * @param dest The destination for the weights.
     * @param source
     */
	public void copyWeights( KohonenNetwork dest , KohonenNetwork source ) {
		for ( int i=0;i<source.outputWeights.length;i++ ) {
			System.arraycopy(source.outputWeights[i],0,dest.outputWeights[i],0,source.outputWeights[i].length);
		}
	}
	
	/**
     * Clear the weights.
     */
	public void clearWeights() {
		totalError = 1.0;
		for ( int y=0;y<outputWeights.length;y++ )
			for ( int x=0;x<outputWeights[0].length;x++ )
				outputWeights[y][x]=0;
	}

	/**
     * Normalize the input.
     *
     * @param input input pattern
     * @param normfac the result
     * @param synth synthetic last input
     */
	void normalizeInput(final double input[] ,double normfac[] ,double synth[]) {
		double length ;
		length = vectorLength ( input ) ;
		// just in case it gets too small
		if ( length < 1.E-30 )
			length = 1.E-30 ;
		
		normfac[0] = 1.0 / Math.sqrt ( length ) ;
		synth[0] = 0.0 ;
	}
	
	/**
     * Normalize weights
     *
     * @param w Input weights
     */
	void normalizeWeight( double w[] ) {
		int i ;
		double len ;
		len = vectorLength ( w ) ;
		// just incase it gets too small
		if ( len < 1.E-30 )
			len = 1.E-30 ;
		
		len = 1.0 / Math.sqrt ( len ) ;
		for ( i=0 ; i<inputNeuronCount ; i++ )
			w[i] *= len ;
		w[inputNeuronCount] = 0;
	}
	
	/**
     * Present an input pattern and get the
     * winning neuron.
     *
     * @param input input pattern
     * @param normfac the result
     * @param synth synthetic last input
     * @return The winning neuron number.
     */
	public int winner(double input[] ,double normfac[] ,double synth[]) {
		int i , win=0;
		double biggest, optr[];
		normalizeInput( input , normfac , synth ) ;  // Normalize input
		biggest = -1.E30;
		for ( i=0 ; i<outputNeuronCount; i++ ) {
			optr = outputWeights[i];
			output[i] = dotProduct (input , optr ) * normfac[0] + synth[0] * optr[inputNeuronCount] ;
			// Remap to bipolar(-1,1 to 0,1)
			output[i] = 0.5 * (output[i] + 1.0) ;
			
			if ( output[i] > biggest ) {
				biggest = output[i] ;
				win = i ;
			}
			// account for rounding
			if ( output[i] > 1.0 )
				output[i] = 1.0 ;
			if ( output[i] < 0.0 )
				output[i] = 0.0 ;
		}
		return win ;
	}
	
	public int[] mywinner(double input[] ,double normfac[] ,double synth[]) {
		int i ;
		int[] win=new int[output.length];
		double biggest, optr[];

		normalizeInput( input , normfac , synth ) ;  // Normalize input

		biggest = -1.E30;
		for ( i=0 ; i<outputNeuronCount; i++ ) {
			optr = outputWeights[i];
			output[i] = dotProduct (input , optr ) * normfac[0] + synth[0] * optr[inputNeuronCount] ;
			// Remap to bipolar(-1,1 to 0,1)
			output[i] = 0.5 * (output[i] + 1.0) ;
			if ( output[i] > biggest ) {
				biggest = output[i] ;
			}          
       
			// account for rounding
			if ( output[i] > 1.0 )
				output[i] = 1.0 ;
			if ( output[i] < 0.0 )
				output[i] = 0.0 ;
		}
		double[] x=new double[output.length];
     
		for(int j=0;j<x.length;j++)
			x[j]=output[j];
		Arrays.sort(x);
		for(int ii=0;ii<x.length;ii++)
		{
			//win[ii]=Arrays.binarySearch(output, x[x.length-1-ii]);
			for(int jj=0;jj<x.length;jj++)
			{
				if(output[jj]==x[x.length-1-ii])
					win[ii]=jj;
			}
		}
		return win ;
	}

	public double[] output(double input[] ,double normfac[] ,double synth[]) {
		int i ;
		double[] win=new double[numberofVote];
		double biggest, optr[];

		normalizeInput( input , normfac , synth ) ;  // Normalize input

		biggest = -1.E30;
		for ( i=0 ; i<outputNeuronCount; i++ ) {
			optr = outputWeights[i];
			output[i] = dotProduct (input , optr ) * normfac[0] + synth[0] * optr[inputNeuronCount] ;
			// Remap to bipolar(-1,1 to 0,1)
			output[i] = 0.5 * (output[i] + 1.0) ;
			if ( output[i] > biggest ) {
				biggest = output[i] ;
			}          
       
			// account for rounding
			if ( output[i] > 1.0 )
				output[i] = 1.0 ;
			if ( output[i] < 0.0 )
				output[i] = 0.0 ;
		}
		double[] x=new double[output.length];
     
		for(int j=0;j<x.length;j++)
			x[j]=output[j];
		Arrays.sort(x);
		for(int ii=0;ii<numberofVote&&ii<x.length;ii++)
		{
			//win[ii]=Arrays.binarySearch(output, x[x.length-1-ii]);
			for(int jj=0;jj<x.length;jj++)
			{
				if(output[jj]==x[x.length-1-ii])
					//win[ii]=jj;
					win[ii]=output[jj];
			}
		}
		return win ;
	}

	
	/**
     * This method does much of the work of the learning process.
     * This method evaluates the weights against the training
     * set.
     *
     * @param rate learning rate
     * @param learn_method method(0=additive, 1=subtractive)
     * @param won a Holds how many times a given neuron won
     * @param bigerr a returns the error
     * @param correc a returns the correction
     * @param work a work area
     * @exception java.lang.RuntimeException
     */
	void evaluateErrors (double rate,int learn_method,int won[],double bigerr[],double correc[][],double work[])throws RuntimeException {
		int best,tset ;
		double dptr[], normfac[] = new double[1];
		double synth[]=new double[1], cptr[], wptr[], length, diff ;

		// reset correction and winner counts
		for ( int y=0;y<correc.length;y++ ) {
			for ( int x=0;x<correc[0].length;x++ ) {
				correc[y][x]=0;
			}
		}
		for ( int i=0;i<won.length;i++ )
			won[i]=0;
		bigerr[0] = 0.0 ;
		// loop through all training sets to determine correction
		for ( tset=0 ; tset<train.getTrainingSetCount(); tset++ ) {
			dptr = train.getInputSet(tset);
			best =winner ( dptr , normfac , synth );
			won[best]++;
			wptr = outputWeights[best];
			cptr = correc[best];
			length = 0.0 ;
			for ( int i=0 ; i<inputNeuronCount ; i++ ) {
				diff = dptr[i] * normfac[0] - wptr[i] ;
				length += diff * diff ;
				if ( learn_method!=0 )
					cptr[i] += diff ;
				else
					work[i] = rate * dptr[i] * normfac[0] + wptr[i] ;
			}
			diff = synth[0] - wptr[inputNeuronCount] ;
			length += diff * diff ;
			if ( learn_method!=0 )
				cptr[inputNeuronCount] += diff ;
			else
				work[inputNeuronCount] = rate * synth[0] + wptr[inputNeuronCount] ;
			
			if ( length > bigerr[0] )
				bigerr[0] = length ;

			if ( learn_method==0 ) {
				normalizeWeight( work ) ;
				for ( int i=0 ; i<=inputNeuronCount ; i++ )
					cptr[i] += work[i] - wptr[i] ;
			}
		}
		bigerr[0] = Math.sqrt ( bigerr[0] ) ;
	}

	/**
     * This method is called at the end of a training iteration.
     * This method adjusts the weights based on the previous trial.
     *
     * @param rate learning rate
     * @param learn_method method(0=additive, 1=subtractive)
     * @param won a holds number of times each neuron won
     * @param bigcorr holds the error
     * @param correc holds the correction
     */
	void adjustWeights (double rate ,int learn_method ,int won[] ,double bigcorr[],double correc[][]){
		
		double corr, cptr[], wptr[], length, f ;
		bigcorr[0] = 0.0 ;

		for ( int i=0 ; i<outputNeuronCount ; i++ ) {
			if ( won[i]==0 )
				continue ;
			wptr = outputWeights[i];
			cptr = correc[i];
			f = 1.0 / (double) won[i] ;
			if ( learn_method!=0 )
				f *= rate ;
			length = 0.0 ;
			for ( int j=0 ; j<=inputNeuronCount ; j++ ) {
				corr = f * cptr[j] ;
				wptr[j] += corr ;
				length += corr * corr ;
			}
			if ( length > bigcorr[0] )
				bigcorr[0] = length ;
		}
		// scale the correction
		bigcorr[0] = Math.sqrt ( bigcorr[0] ) / rate ;
	}

	/**
     * If no neuron wins, then force a winner.
     *
     * @param won how many times each neuron won
     * @exception java.lang.RuntimeException
     */
	void forceWin(int won[])throws RuntimeException {
		int i, tset, best, which=0;
		double dptr[], normfac[]=new double[1];
		double synth[] = new double[1], dist, optr[];

		dist = 1.E30 ;
		for ( tset=0 ; tset<train.getTrainingSetCount() ; tset++ ) {
			dptr = train.getInputSet(tset);
			best = winner ( dptr , normfac , synth );
			if ( output[best] < dist ) {
				dist = output[best] ;
				which = tset ;
			}
		}
		dptr = train.getInputSet(which);
		best = winner ( dptr , normfac , synth );
		dist = -1.e30 ;
		i = outputNeuronCount;
		while ( (i--)>0 ) {
			if ( won[i]!=0 )
				continue ;
			if ( output[i] > dist ) {
				dist = output[i] ;
				which = i ;
			}
		}
		optr = outputWeights[which];
		System.arraycopy(dptr,0,optr,0,dptr.length);
		optr[inputNeuronCount] = synth[0] / normfac[0] ;
		normalizeWeight ( optr ) ;
	}

	/**
     * This method is called to train the network. It can run
     * for a very long time and will report progress back to the
     * owner object.
     *
     * @exception java.lang.RuntimeException
     */
	public void learn ()throws RuntimeException {
		int i, tset,iter,n_retry;
		int won[],winners ;
		double work[],correc[][],rate,best_err,dptr[];
		double bigerr[] = new double[1] ;
		double bigcorr[] = new double[1];
		KohonenNetwork bestnet;  // Preserve best here

		totalError = 1.0 ;

		for ( tset=0 ; tset<train.getTrainingSetCount(); tset++ ) {
			dptr = train.getInputSet(tset);
			if ( vectorLength( dptr ) < 1.E-30 ) {
				throw(new RuntimeException("Multiplicative normalization has null training case")) ;
			}
		}
		bestnet = new KohonenNetwork(inputNeuronCount,outputNeuronCount,numberofVote);//,owner) ;
		won = new int[outputNeuronCount];
		correc = new double[outputNeuronCount][inputNeuronCount+1];
		if ( learnMethod==0 )
			work = new double[inputNeuronCount+1];
		else
			work = null ;
		rate = learnRate;
		initialize () ;
		best_err = 1.e30 ;
		
		// main loop:
		n_retry = 0 ;
		for ( iter=0 ; ; iter++ ) {
			evaluateErrors ( rate , learnMethod , won ,bigerr , correc , work ) ;
			totalError = bigerr[0] ;
			if ( totalError < best_err ) {
				best_err = totalError ;
				copyWeights ( bestnet , this ) ;
			}
			winners = 0 ;
			for ( i=0;i<won.length;i++ )
				if ( won[i]!=0 )
					winners++;
			if ( bigerr[0] < quitError )
				break ;
			if ( (winners < outputNeuronCount)  && (winners < train.getTrainingSetCount()) ) {
				forceWin ( won ) ;
				continue ;
			}
			adjustWeights ( rate , learnMethod , won , bigcorr, correc ) ;
			if ( halt ) {
				break;
			}
			Thread.yield();
			if ( bigcorr[0] < 1E-5 ) {
				if ( ++n_retry > retries )
					break ;
				initialize () ;
				iter = -1 ;
				rate = learnRate ;
				continue ;
			}
			if ( rate > 0.00000001 ){
				//rate *= reduction ;
				rate = learnRate * Math.exp(-1.0*(iter/(retries/Math.log(inputNeuronCount/4))));
				System.out.println("rate : "+rate);
			}
		}
		// done
		copyWeights( this , bestnet ) ;
		for ( i=0 ; i<outputNeuronCount ; i++ )
			normalizeWeight ( outputWeights[i] ) ;
		halt = true;
		n_retry++;
		
	}

	/**
     * Called to initialize the Kononen network.
     */
	public void initialize() {
		int i ;
		double optr[];
		clearWeights() ;
		randomizeWeights( outputWeights ) ;
		for ( i=0 ; i<outputNeuronCount ; i++ ) {
			optr = outputWeights[i];
			normalizeWeight( optr );
		}
	}
	
	/**
	 * Calculate the length of a vector.
	 *
	 * @param v vector
	 * @return Vector length.
	 */
	double vectorLength( double v[] ) {
		double rtn = 0.0 ;
	    for ( int i=0;i<v.length;i++ )
	    	rtn += v[i] * v[i];
	    return rtn;
	}

	/**
	 * Called to calculate a dot product.
	 *
	 * @param vec1 one vector
	 * @param vec2 another vector
	 * @return The dot product.
	 */
	double dotProduct(double vec1[] , double vec2[] ) {
		int k, m,v;
	    double rtn;

	    rtn = 0.0;
	    k = vec1.length / 4;
	    m = vec1.length % 4;

	    v = 0;
	    while ( (k--)>0 ) {
	    	rtn += vec1[v] * vec2[v];
	    	rtn += vec1[v+1] * vec2[v+1];
	        rtn += vec1[v+2] * vec2[v+2];
	        rtn += vec1[v+3] * vec2[v+3];
	        v+=4;
	    }
	    while ( (m--)>0 ) {
	    	rtn += vec1[v] * vec2[v];
	        v++;
	    }
	    return rtn;
	}
	
	/**
	 * Called to randomize weights.
	 *
	 * @param weight A weight matrix.
	 */
	void randomizeWeights( double weight[][] ) {
		double r ;
		int temp = (int)(3.464101615 / (2. * Math.random() )) ; // SQRT(12)=3.464...

		for ( int y=0;y<weight.length;y++ ) {
			for ( int x=0;x<weight[0].length;x++ ) {
				r = (double) random.nextInt(Integer.MAX_VALUE) + (double) random.nextInt(Integer.MAX_VALUE) - (double) random.nextInt(Integer.MAX_VALUE) - (double) random.nextInt(Integer.MAX_VALUE) ;
				weight[y][x] = temp * r ;
			}
		}
	}
}