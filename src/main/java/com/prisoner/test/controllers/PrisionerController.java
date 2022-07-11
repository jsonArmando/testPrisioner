package com.prisoner.test.controllers;

import com.prisoner.test.entities.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.prisoner.test.entities.Prosioner;
import com.prisoner.test.exceptions.DnaExcepcion;
import com.prisoner.test.services.PrisionerService;

@RestController
public class PrisionerController {

    @Autowired
    private PrisionerService prisionerService;

    @RequestMapping(method = RequestMethod.POST, value = "/prosioner/")
	@ResponseBody
	public ResponseEntity<String> isPrisioner(@RequestBody Prosioner prosioner) throws DnaExcepcion {

        String[] dna = prosioner.getDna().toArray(new String[0]);
        boolean isPrisioner = prisionerService.getProsionerStatus(dna);

        ResponseEntity<String> response;

		if (isPrisioner) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return response;
    }

	@RequestMapping("/stats")
	public Stats getStats() {
		return prisionerService.getStats();
	}
    
}
