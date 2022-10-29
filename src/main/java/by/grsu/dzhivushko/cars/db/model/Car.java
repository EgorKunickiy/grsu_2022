package by.grsu.dzhivushko.cars.db.model;

import java.sql.Timestamp;

public class Car {

	private Integer id;

	private String vin;

	private Integer modelId;

	private Timestamp created;
	
	private Timestamp updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", vin=" + vin + ", modelId=" + modelId + ", created=" + created + "]";
	}
}
