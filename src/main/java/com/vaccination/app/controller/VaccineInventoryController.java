package com.vaccination.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaccination.app.entity.VaccineInventory;
import com.vaccination.app.exception.VaccineInventoryException;
import com.vaccination.app.sevice.VaccineInventoryService;

@RestController
public class VaccineInventoryController {

	@Autowired
	private VaccineInventoryService vaccineInventoryService;
	
	
	
	
	@PostMapping("/vaccineInventory")
	public VaccineInventory addVaccineInventory(@RequestBody VaccineInventory vaccineInventory) throws VaccineInventoryException {
		return this.vaccineInventoryService.addVaccineInventory(vaccineInventory);
	}
	
	@GetMapping("/vaccineInventory/{centerId}")
	public VaccineInventory getVaccineInventoryByCenterId(@PathVariable("centerId") Integer centerId) throws VaccineInventoryException {
		return this.vaccineInventoryService.getVaccineInventoryById(centerId);
	}
	
	@GetMapping("/allVaccineInventories/{date}")
	public List<VaccineInventory> getAllVaccineInventoriesByDate(@PathVariable("date") LocalDate date) throws VaccineInventoryException {
		return this.vaccineInventoryService.getVaccineInventoryBydate(date);
	}
	
	@GetMapping("/allVaccineInventories")
	public List<VaccineInventory> getAllVaccineInventories() throws VaccineInventoryException {
		return this.vaccineInventoryService.allVaccineInventory();
	}
	
	@PutMapping("/vaccineInventory")
	public VaccineInventory updateVaccineInventory(@RequestBody VaccineInventory vaccineInventory) throws VaccineInventoryException {
		return this.vaccineInventoryService.updateVaccineInventory(vaccineInventory);
	}
	
	@DeleteMapping("/vaccineInventory/{inventoryId}")
	public Boolean deleteVaccineInventory(@PathVariable("inventoryId") Integer inventoryId) throws VaccineInventoryException {
		return this.vaccineInventoryService.deleteVaccineInventory(inventoryId);
	}
}
