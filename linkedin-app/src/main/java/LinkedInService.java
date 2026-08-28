import java.util.*;
public class LinkedInService {
    private final Map<Integer,User> users=new HashMap<>();
    private final List<Connection> connections=new ArrayList<>();
    private final List<JobPosting> jobs=new ArrayList<>();
    private final NotificationService notifications=new NotificationService();
    private int connectionId=1,messageId=1;
    public void registerUser(User u){
        users.put(u.getId(),u);notifications.subscribe(u);
    }
    public void sendConnectionRequest(User from,User to){
        if(from==to||from.isConnectedTo(to))return;
        Connection c=new Connection(connectionId++,from,to);
        connections.add(c);notifications.notifyUser(to,NotificationType.CONNECTION_REQUEST,from.getName()+" sent you a connection request.");
    }
 public void acceptConnection(User from,User to){
        Connection c=find(from,to);if(c==null||c.getStatus()!=ConnectionStatus.PENDING)return;
        c.accept();from.addConnection(to);to.addConnection(from);
        notifications.notifyUser(from,NotificationType.CONNECTION_ACCEPTED,to.getName()+" accepted your connection request.");
    }
 private Connection find(User from,User to){
        for(Connection c:connections)
            if(c.getSender()==from&&c.getReceiver()==to)
                return c;return null;
    }
 public void sendMessage(User from,User to,String text){
        if(!from.isConnectedTo(to))return;
        Message m=new Message(messageId++,from,to,text);from.addMessage(m);
        to.addMessage(m);notifications.notifyUser(to,NotificationType.MESSAGE,"New message from "+from.getName());
    }
 public void postJob(JobPosting j){
        jobs.add(j);
    }
    public List<JobPosting> searchJobs(String key){
        List<JobPosting> r=new ArrayList<>();
        for(JobPosting j:jobs)if((j.getTitle()+" "+j.getDescription()).toLowerCase().contains(key.toLowerCase()))r.add(j);return r;
    }
    public List<User> searchUsers(String key){
        List<User> r=new ArrayList<>();
        for(User u:users.values())if(u.getName().toLowerCase().contains(key.toLowerCase()))r.add(u);return r;
    }
    public List<JobPosting> getJobPostings(){
        return jobs;
    }
}
