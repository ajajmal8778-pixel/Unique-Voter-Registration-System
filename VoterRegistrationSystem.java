import java.sql.*;

public class VoterRegistrationSystem {

    // Add voter
    public boolean addVoter(Voter v) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO voters VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, v.getVoterId());
            ps.setString(2, v.getName());
            ps.setInt(3, v.getAge());
            ps.setString(4, v.getAddress());
            ps.setString(5, v.getFatherMotherName());
            ps.setString(6, v.getGender());
            ps.setString(7, v.getMobile());
            ps.setString(8, v.getEmail());
            ps.setString(9, v.getAadhaar());
            ps.setString(10, v.getDob());
            ps.setString(11, v.getNationality());
            ps.setString(12, v.getDistrict());
            ps.setString(13, v.getState());
            ps.setString(14, v.getPincode());
            ps.setString(15, v.getConstituency());
            ps.setString(16, v.getBoothNo());
            ps.setString(17, v.getVoterType());
            ps.setString(18, v.getRegistrationDate());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate voter ID!");
            return false;

        } catch (Exception e) {
            System.out.println("Error inserting voter: " + e);
            return false;
        }
    }

    // Show all voters
    public void showVoters() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM voters");

            while (rs.next()) {
                System.out.println(
                    "\nVoter ID: " + rs.getString("voterId") +
                    "\nName: " + rs.getString("name") +
                    "\nAge: " + rs.getInt("age") +
                    "\nAddress: " + rs.getString("address")
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error reading voters: " + e);
        }
    }

    // Search voter
    public void searchVoter(String id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM voters WHERE voterId=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nVoter Found!");
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Address: " + rs.getString("address"));
            } else {
                System.out.println("Voter not found.");
            }
            con.close();

        } catch (Exception e) {
            System.out.println("Error searching voter: " + e);
        }
    }

    // Delete voter
    public void deleteVoter(String id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM voters WHERE voterId=?");
            ps.setString(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Voter deleted successfully.");
            else
                System.out.println("Voter ID not found.");

            con.close();

        } catch (Exception e) {
            System.out.println("Error deleting voter: " + e);
        }
    }

    // Count voters
    public void countVoters() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM voters");

            if (rs.next()) {
                System.out.println("Total Registered Voters: " + rs.getInt(1));
            }
            con.close();

        } catch (Exception e) {
            System.out.println("Error counting voters: " + e);
        }
    }
}
