package fr.andromede.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class SequencerBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(SequencerBuilder.class);
	
	public static final String SEQUENCE_NAME = "sequenceInternalDB";
	public static final String SEQUENCE_FIELD = "SEQ";
	
	private static SequencerBuilder INSTANCE = null;
	
	private DB db;
	public String collectionName;

	public static SequencerBuilder create() {
		if ( INSTANCE == null ) {
			INSTANCE = new SequencerBuilder();
		}
		return INSTANCE;
	}

	public SequencerBuilder setDB(DB db) {
		this.db = db;
		return this;
	}

	public SequencerBuilder setCollection(String collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	public String getNextID() throws DataAccessException {
		if ((this.db == null) || (this.collectionName == null)) {
			throw new DataIntegrityViolationException("DataBase unknown or collection name unknow.");
		}
		return getNextID(this.db, this.collectionName);
	}

	public static void resetAllSequencer(DB db) {
		LOGGER.trace("============================== DAO ================================");
		LOGGER.trace("START -- resetAllSequencer({})");
		if (db.collectionExists(SEQUENCE_NAME)) {
			DBCollection collections = db.getCollection(SEQUENCE_NAME);
			LOGGER.trace("Séquenceur [{}] vidé.", collections.toString());
			collections.drop();
		}
		LOGGER.trace("===================================================================");
	}

	public static String getNextID(DB db, String collectionName) {
		LOGGER.trace("============================== DAO ================================");
		LOGGER.trace("START -- getNextId({})", collectionName);

		DBObject query = new BasicDBObject();
		query.put("_id", collectionName);

		DBObject change = new BasicDBObject(SEQUENCE_FIELD, Integer.valueOf(1));
		DBObject update = new BasicDBObject("$inc", change);

		DBObject res = db.getCollection(SEQUENCE_NAME).findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
		String nextID = res.get(SEQUENCE_FIELD).toString();
		LOGGER.trace("ID du séquenceur pour [{}] : [{}].", collectionName, nextID);
		LOGGER.trace("===================================================================");
		return nextID;
	}

}
