package at.time.amendment.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import at.time.amendment.model.Record;
import at.time.amendment.model.User;
import at.time.amendment.service.AmendmentService;
import at.time.amendment.service.RecordService;

@ApplicationScoped // not so the yellow from the egg
@Named
public class AmendmentBean implements Serializable {

	private static final long serialVersionUID = 4415574393568154183L;

	@Inject
	private AmendmentService amendmentService;

	@Inject
	private RecordService recordService;

	private Date begin;
	private Date end;
	private User user;
	private List<Record> records;

	@PostConstruct
	private void startConsumer(@Observes @Initialized(ApplicationScoped.class) final Object init) {
		setRecords(recordService.getAll());
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(final Date begin) {
		this.begin = begin;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(final List<Record> records) {
		this.records = records;
	}

	public void refreshRecords() {
		setRecords(recordService.getAll());
	}

	public void onRowEdit(final RowEditEvent event) {
		final Record record = (Record) event.getObject();
		amendmentService.saveRecord(record);
		final FacesMessage msg = new FacesMessage("Record Edited", record.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(final RowEditEvent event) {
		final FacesMessage msg = new FacesMessage("Edit Cancelled", ((Record) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
