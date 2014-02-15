package fr.andromede.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;

public abstract class AbstractDAO<T> implements DAO<T> {

	@Resource(name="mongoTemplate")
	private MongoOperations template;
	
	private SequencerBuilder sequencer;

	public MongoOperations getTemplate() {
		return this.template;
	}

	public void setTemplate(MongoOperations template) {
		this.template = template;
	}

	public SequencerBuilder getSequencer() {
		return this.sequencer;
	}

	public void setSequencer(SequencerBuilder sequencer) {
		this.sequencer = sequencer;
	}	

}
