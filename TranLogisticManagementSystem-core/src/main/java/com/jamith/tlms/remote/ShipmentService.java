package com.jamith.tlms.remote;

import jakarta.ejb.Remote;

@Remote
public interface ShipmentService {
    void shipmentOrganizer();
}
