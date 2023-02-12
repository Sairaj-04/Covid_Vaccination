package com.vaccination.app.sevice;

import java.time.LocalDate;
import java.util.List;

import com.vaccination.app.entity.VaccineInventory;
import com.vaccination.app.exception.VaccineInventoryException;

public interface VaccineInventoryService {
	
		public VaccineInventory addVaccineInventory(VaccineInventory inv) throws VaccineInventoryException;
	
		public List<VaccineInventory> allVaccineInventory() throws VaccineInventoryException;	
		
		public VaccineInventory getVaccineInventoryById(Integer centerId) throws VaccineInventoryException;
		
		public VaccineInventory updateVaccineInventory(VaccineInventory vaccineInv) throws VaccineInventoryException;
		
		public Boolean deleteVaccineInventory(Integer inventoryId) throws VaccineInventoryException;
		
		public List<VaccineInventory> getVaccineInventoryBydate(LocalDate date)throws VaccineInventoryException;
		
}
