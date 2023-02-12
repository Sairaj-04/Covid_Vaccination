package com.vaccination.app.sevice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaccination.app.entity.VaccinationCenter;
import com.vaccination.app.entity.VaccineCount;
import com.vaccination.app.entity.VaccineInventory;
import com.vaccination.app.exception.VaccineInventoryException;
import com.vaccination.app.repository.VaccinationCenterRepository;
import com.vaccination.app.repository.VaccineCountRepository;
import com.vaccination.app.repository.VaccineInventoryRepository;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {

	@Autowired
	private VaccineInventoryRepository vaccineInventoryRepository;

	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepository;

	@Autowired
	private VaccineCountRepository vaccineCountRepository;

	@Override
	public VaccineInventory addVaccineInventory(VaccineInventory inv) throws VaccineInventoryException {

		return vaccineInventoryRepository.save(inv);
	}

	@Override
	public List<VaccineInventory> allVaccineInventory() throws VaccineInventoryException {

		List<VaccineInventory> list = vaccineInventoryRepository.findAll();

		if (list.size() > 0) {
			return list;
		}

		throw new VaccineInventoryException("List empty, need to add Inventory first!");
	}

	@Override
	public VaccineInventory getVaccineInventoryById(Integer centerId) throws VaccineInventoryException {

		Optional<VaccinationCenter> vc = vaccinationCenterRepository.findById(centerId);

		if (vc.isPresent()) {

			if (vc.get().getInventory() != null) {

				return vc.get().getInventory();

			} else {

				throw new VaccineInventoryException("Please Add some Inventory");

			}

		}

		throw new VaccineInventoryException("Please enter valid center Id");

	}

	@Override
	public VaccineInventory updateVaccineInventory(VaccineInventory vaccineInv) throws VaccineInventoryException {

		Optional<VaccineInventory> opt = vaccineInventoryRepository.findById(vaccineInv.getInventoryId());

		if (opt.isPresent()) {

			List<VaccinationCenter> centerList = opt.get().getListvaccinationCenter();

			for (VaccinationCenter vaccinationCenter : centerList) {

				vaccinationCenter.setInventory(vaccineInv);

				vaccinationCenterRepository.save(vaccinationCenter);

			}

			List<VaccineCount> vaccineCounts = opt.get().getVaccinecountList();

			for (VaccineCount vaccineCount : vaccineCounts) {

				vaccineCount.setInventory(vaccineInv);

				vaccineCountRepository.save(vaccineCount);

			}

			return vaccineInventoryRepository.save(vaccineInv);

		}

		throw new VaccineInventoryException("Vaccine Inventory not found!");
	}

	@Override
	public Boolean deleteVaccineInventory(Integer inventoryId) throws VaccineInventoryException {

		Optional<VaccineInventory> opt = vaccineInventoryRepository.findById(inventoryId);

		if (opt.isPresent()) {

			vaccineInventoryRepository.deleteById(inventoryId);
			return true;
		}

		throw new VaccineInventoryException("Vaccine Inventory Not Found");
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryBydate(LocalDate date) throws VaccineInventoryException {
		List<VaccineInventory> inventoryList = vaccineInventoryRepository.getInventoryByDate(date); // ? should br
																									// findInventoryByDate

		if (inventoryList.size() > 0) {
			return inventoryList;
		}

		throw new VaccineInventoryException("No vaccine Found on this date");
	}

}
