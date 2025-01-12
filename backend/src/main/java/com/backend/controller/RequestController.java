package com.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Request;
import com.backend.service.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController {
	@Autowired
	private RequestService service;
	// Get all user
    @GetMapping
    public List<Request> getAllRequest() {
        return service.getAllRequest();
    }

    // Get a user by ID
    @GetMapping("/{rid}")
    public ResponseEntity<Request> getRequestById(@PathVariable int rid) {
        Optional<Request> request = service.getRequestById(rid);
        return request.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Create New User
	@PostMapping
	public Request addRequest(@RequestBody Request request) {
		return service.addRequest(request);
	}
	 // Update an existing user
    @PutMapping("/{rid}")
    public ResponseEntity<Request> updateRequest(@PathVariable int rid, @RequestBody Request request) {
        Request updatedRequest = service.updateRequest(rid, request);
        return updatedRequest != null ? ResponseEntity.ok(updatedRequest) : ResponseEntity.notFound().build();
    }

    // Delete a user
    @DeleteMapping("/{rid}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int rid) {
        return service.deleteRequest(rid) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
