package transientwatch.com.transientwatch.Model;

import android.app.ListActivity;

import java.io.Serializable;

public class Transient implements Serializable {

    private boolean followed;
	private String name;
    private String type;
    private String right_ascention;
	private String declination;
	private String orbital_period;
	private String MAXI_prob_change;
	private String MAXI_average_flux;
	private String MAXI_data;

	private String SWIFT_BAT_prob_change;
	private String SWIFT_BAT_average_flux;
	private String SWIFT_BAT_data;

	private String FERMI_GBM_prob_change;
	private String FERMI_GBM_average_flux;
	private String FERMI_GBM_data;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getRight_ascention() {
		return right_ascention;
	}
	public void setRight_ascention(String right_ascention) {
		this.right_ascention = right_ascention;
	}
	public String getDeclination() {
		return declination;
	}
	public void setDeclination(String declination) {
		this.declination = declination;
	}
	public String getOrbital_period() {
		return orbital_period;
	}
	public void setOrbital_period(String orbital_period) {
		this.orbital_period = orbital_period;
	}
	public String getMAXI_prob_change() {
		return MAXI_prob_change;
	}
	public void setMAXI_prob_change(String mAXI_prob_change) {
		MAXI_prob_change = mAXI_prob_change;
	}
	public String getMAXI_average_flux() {
		return MAXI_average_flux;
	}
	public void setMAXI_average_flux(String mAXI_average_flux) {
		MAXI_average_flux = mAXI_average_flux;
	}
	public String getMAXI_data() {
		return MAXI_data;
	}
	public void setMAXI_data(String mAXI_data) {
		MAXI_data = mAXI_data;
	}
	public String getSWIFT_BAT_prob_change() {
		return SWIFT_BAT_prob_change;
	}
	public void setSWIFT_BAT_prob_change(String sWIFT_BAT_prob_change) {
		SWIFT_BAT_prob_change = sWIFT_BAT_prob_change;
	}
	public String getSWIFT_BAT_average_flux() {
		return SWIFT_BAT_average_flux;
	}
	public void setSWIFT_BAT_average_flux(String sWIFT_BAT_average_flux) {
		SWIFT_BAT_average_flux = sWIFT_BAT_average_flux;
	}
	public String getSWIFT_BAT_data() {
		return SWIFT_BAT_data;
	}
	public void setSWIFT_BAT_data(String sWIFT_BAT_data) {
		SWIFT_BAT_data = sWIFT_BAT_data;
	}
	public String getFERMI_GBM_prob_change() {
		return FERMI_GBM_prob_change;
	}
	public void setFERMI_GBM_prob_change(String fERMI_GBM_prob_change) {
		FERMI_GBM_prob_change = fERMI_GBM_prob_change;
	}
	public String getFERMI_GBM_average_flux() {
		return FERMI_GBM_average_flux;
	}
	public void setFERMI_GBM_average_flux(String fERMI_GBM_average_flux) {
		FERMI_GBM_average_flux = fERMI_GBM_average_flux;
	}
	public String getFERMI_GBM_data() {
		return FERMI_GBM_data;
	}
	public void setFERMI_GBM_data(String fERMI_GBM_data) {
		FERMI_GBM_data = fERMI_GBM_data;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
	
}


// X Per | 58.846 | 31.046 | 250.3 | 74.5% | 30.7 | Apr 11th | 9.8% | 35.8 | Apr 10th |  |  | NO DATA | PLOT | 