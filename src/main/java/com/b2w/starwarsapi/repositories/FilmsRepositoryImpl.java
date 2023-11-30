package com.b2w.starwarsapi.repositories;

import static com.mongodb.client.model.Filters.eq;

// import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import static com.mongodb.client.model.ReturnDocument.AFTER;

import com.b2w.starwarsapi.models.Films;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;

@Repository
public class FilmsRepositoryImpl implements FilmsRepository {
	private static final String collectionName = "films";

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
	
	@Autowired
    private MongoClient client;
	
	private MongoCollection<Films> filmCollection;

    @PostConstruct
	void init() {
		filmCollection = client.getDatabase(databaseName).getCollection(collectionName, Films.class);
	}

    @Override
	public Films save(Films films) {
		films.setId(new ObjectId());
		filmCollection.insertOne(films);
		return films; 
	}

	@Override
	public List<Films> findAll() {
		return filmCollection.find().into(new ArrayList<>());
	}

	
	// @Override
	// public List<Films> findByName(String title) {
	// 	return filmCollection.find(Filters.eq("title", title), Films.class).into(new ArrayList<>());
	// }

	
	@Override
	public Optional<Films> findOne(String id) {
		Films films = filmCollection.find(eq("_id", new ObjectId(id))).first();
		return Optional.ofNullable(films);
	}

	
	// @Override
	// public long episode_id() {
	// 	return filmCollection.countDocuments();
	// }

	
	@Override
	public long delete(String id) {
		return filmCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
	}
	

	@Override
	public long deleteAll() {
		return filmCollection.deleteMany(new BsonDocument()).getDeletedCount();
	}
	
	
	@Override
	public Films update(Films films) {
		FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return filmCollection.findOneAndReplace(eq("_id", films.getId()), films, options);
	}

	@Override
	public List<Films> findByName(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findByName'");
	}
}
