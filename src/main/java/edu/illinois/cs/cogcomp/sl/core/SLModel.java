package edu.illinois.cs.cogcomp.sl.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.illinois.cs.cogcomp.sl.util.Lexiconer;
import edu.illinois.cs.cogcomp.sl.util.WeightVector;

/**
 * The class that allows the future implementations of a learned model.
 * <p>
 * 
 * To save and load of this function, please refer to {@link JLISModelIOManager}
 * <p>
 * 
 * 
 * @author Cogcomp @ UI
 * 
 */
public class SLModel implements Serializable {

	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(SLModel.class);

	public WeightVector wv;
	public SLParameters para;
	public Lexiconer lm;
	public AbstractInferenceSolver infSolver;
	public AbstractFeatureGenerator featureGenerator;
	public Map<String, String> config;

	/**
	 * The function that is used to save the model into disk. This function just
	 * serialize the whole object into disk.
	 * 
	 * You can modify the save/load behavior by overriding this function.
	 * 
	 * @param fileName
	 *            The filename of the saved model.
	 * @throws IOException
	 */
	public void saveModel(String fileName) throws IOException {
		logger.info("Save Model to " + fileName + ".....");
		ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName)));
		oos.writeObject(this);
		oos.close();
		logger.info("Done!");
	}

	/**
	 * The function is used to load the model. You can modify the save/load
	 * behavior by overriding this function.
	 * 
	 * @param fileName
	 *            The filename of the saved model.
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static SLModel loadModel(String fileName) throws IOException,
			ClassNotFoundException {
		logger.info("Load trained Models.....");

		SLModel res = null;
		ObjectInputStream ios = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream(fileName)));

		res = (SLModel) ios.readObject();
		ios.close();

		logger.info("Load Model complete!");
		return res;
	}
}
