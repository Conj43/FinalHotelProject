import java.util.ArrayList;
import java.util.List;

public class ServiceRequest {
    private String requestType;
    private int quantity;
    private String notes;  
    
    public ServiceRequest(String requestType, int quantity, String notes) {
        this.requestType = requestType;
        this.quantity = quantity;
        this.notes = notes;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    
    public void addServiceRequest(ServiceRequest request) {
        serviceRequests.add(request);
    }

    public List<ServiceRequest> getServiceRequests() {
        return serviceRequests;
    }
}