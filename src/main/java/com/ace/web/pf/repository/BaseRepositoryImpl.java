package com.ace.web.pf.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.domain.Specification;

public class BaseRepositoryImpl implements BaseRepository {
	@PersistenceContext
	public EntityManager em;

	public EntityManager getEntityManager() {
		return this.em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> DataTablesOutput<T> findAll(BaseRepository repo, DataTablesInput dataTablesInput,
			String[] tableColumns) {

		DataTablesOutput<T> output = new DataTablesOutput<T>();

		Long totalCnt = ((DataTablesRepository<T, ?>) repo).count();

		if (totalCnt <= 0) {
			return output;
		}
		Pageable page = getPage(dataTablesInput, totalCnt);

		Page<T> containers = null;

		if (dataTablesInput.getSearch().getValue() == null || dataTablesInput.getSearch().getValue().isEmpty()) {
			containers = ((DataTablesRepository<T, ?>) repo).findAll(page);
		} else {
			Specification<T> advancedSearch = advancedSearch(tableColumns, dataTablesInput.getSearch().getValue());
			containers = ((DataTablesRepository<T, ?>) repo).findAll(advancedSearch, page);
		}

		output.setData(containers.getContent());
		output.setDraw(dataTablesInput.getDraw());
		output.setRecordsFiltered(containers.getTotalElements());
		output.setRecordsTotal(totalCnt);

		return output;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> DataTablesOutput<T> findAll(BaseRepository repo, DataTablesInput dataTablesInput, String[] tableColumns,
			Specification<T> spec) {

		DataTablesOutput<T> output = new DataTablesOutput<T>();
		Long totalCnt = ((DataTablesRepository<T, ?>) repo).count(spec);

		if (totalCnt <= 0) {
			return output;
		}
		Pageable page = getPage(dataTablesInput, totalCnt);

		Page<T> containers = null;

		if (dataTablesInput.getSearch().getValue() == null || dataTablesInput.getSearch().getValue().isEmpty()) {
			containers = ((DataTablesRepository<T, ?>) repo).findAll(spec, page);
		} else {
			Specification<T> advancedSearch = advancedSearch(tableColumns, dataTablesInput.getSearch().getValue());
			containers = ((DataTablesRepository<T, ?>) repo).findAll(Specification.where(spec).and(advancedSearch),
					page);
		}

		output.setData(containers.getContent());
		output.setDraw(dataTablesInput.getDraw());
		output.setRecordsFiltered(containers.getTotalElements());
		output.setRecordsTotal(totalCnt);

		return output;
	}

	private Pageable getPage(DataTablesInput dataTablesInput, Long totalCnt) {
		Integer start = dataTablesInput.getStart();
		Integer length = (int) (dataTablesInput.getLength() < 0 ? totalCnt : dataTablesInput.getLength());

		if (start > 0) {
			start = start / length;
		}

		String sortColName = dataTablesInput.getColumns().get(dataTablesInput.getOrder().get(0).getColumn()).getData();

		String dir = dataTablesInput.getOrder().get(0).getDir();
		Sort sort = null;
		if (dir.equals("asc")) {
			sort = Sort.by(sortColName).ascending();
		} else {
			sort = Sort.by(sortColName).descending();
		}

		return PageRequest.of(start, length, sort);
	}

	private static <T> Specification<T> advancedSearch(String[] columnNames, String value) {
		return new Specification<T>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<Predicate>();

				for (String columnName : columnNames) {

					String[] paths = columnName.split("\\.");

					Path<String> rootPath = null;

					for (int i = 0; i < paths.length; i++) {
						if (i == 0) {
							rootPath = root.get(paths[i]);
						} else {
							rootPath = rootPath.get(paths[i]);
						}
					}

					Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(rootPath.as(String.class)),
							"%" + value.toLowerCase() + "%");
					predicates.add(predicate);

				}
				return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

}
