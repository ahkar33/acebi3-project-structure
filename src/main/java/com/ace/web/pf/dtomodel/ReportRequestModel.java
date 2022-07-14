package com.ace.web.pf.dtomodel;

import java.util.Collection;
import java.util.Map;

import com.ace.web.pf.util.ApplicationData;

public class ReportRequestModel {

	private Map<String, Object> parameters;
	private String reportNameWithoutExt;
	private String exportFileNameWithoutExt;
	private String metaDataTitle;
	private Collection<?> dataSource;

	public ReportRequestModel(String reportName, String exportFileName) {
		
		this.reportNameWithoutExt = ApplicationData.REPORT_ROOT_FOLDER + reportName + ApplicationData.REPORT_EXTENSION;
		this.exportFileNameWithoutExt = exportFileName;
	}

	public String getMetaDataTitle() {
		return metaDataTitle;
	}

	public void setMetaDataTitle(String metaDataTitle) {
		this.metaDataTitle = metaDataTitle;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public String getReportNameWithoutExt() {
		return reportNameWithoutExt;
	}

	public String getExportFileNameWithoutExt() {
		return exportFileNameWithoutExt;
	}

	public Collection<?> getDataSource() {
		return dataSource;
	}

	public void setDataSource(Collection<?> dataSource) {
		this.dataSource = dataSource;
	}

}
