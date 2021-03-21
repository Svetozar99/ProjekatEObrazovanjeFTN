package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos;

import java.util.Date;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPart;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartStatus;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.ExamPartType;

public class ExamPartDTO {

	private Long id;
	private Date date;
	private String location;
	private Integer points;
	private ExamDTO examDTO;
	private ExamPartTypeDTO examPartTypeDTO;
	private ExamPartStatusDTO statusDTO;
	
	public ExamPartDTO(Long id, Date date, String location, Integer points, ExamDTO examDTO,
			ExamPartTypeDTO examPartTypeDTO, ExamPartStatusDTO statusDTO) {
		super();
		this.id = id;
		this.date = date;
		this.location = location;
		this.points = points;
		this.examDTO = examDTO;
		this.examPartTypeDTO = examPartTypeDTO;
		this.statusDTO = statusDTO;
	}

	public ExamPartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExamPartDTO(ExamPart e) {
		this(e.getId(),e.getDate(),e.getLocation(),e.getPoints(),new ExamDTO(e.getExam()),
				new ExamPartTypeDTO(e.getExamPartType()),new ExamPartStatusDTO(e.getExamPartStatus()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public ExamDTO getExamDTO() {
		return examDTO;
	}

	public void setExamDTO(ExamDTO examDTO) {
		this.examDTO = examDTO;
	}

	public ExamPartTypeDTO getExamPartTypeDTO() {
		return examPartTypeDTO;
	}

	public void setExamPartTypeDTO(ExamPartTypeDTO examPartTypeDTO) {
		this.examPartTypeDTO = examPartTypeDTO;
	}

	public ExamPartStatusDTO getStatusDTO() {
		return statusDTO;
	}

	public void setStatusDTO(ExamPartStatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}
	
}
