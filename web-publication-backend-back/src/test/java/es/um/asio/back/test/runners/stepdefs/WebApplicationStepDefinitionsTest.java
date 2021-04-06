package es.um.asio.back.test.runners.stepdefs;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assume;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import es.um.asio.back.controller.book.BookController;
import es.um.asio.back.controller.patent.PatentController;
import es.um.asio.back.controller.project.ProjectController;
import es.um.asio.service.dto.BookDto;
import es.um.asio.service.dto.InvoiceDto;
import es.um.asio.service.dto.PatentDto;
import es.um.asio.service.dto.PersonDetailDto;
import es.um.asio.service.dto.PersonDto;
import es.um.asio.service.dto.ProjectDto;
import es.um.asio.service.dto.UniversityDto;
import es.um.asio.service.filter.book.BookFilter;
import es.um.asio.service.filter.invoice.InvoiceFilter;
import es.um.asio.service.filter.patent.PatentFilter;
import es.um.asio.service.filter.person.PersonFilter;
import es.um.asio.service.filter.project.ProjectFilter;
import es.um.asio.service.filter.university.UniversityFilter;
import es.um.asio.service.proxy.book.BookProxy;
import es.um.asio.service.proxy.book.impl.BookProxyImpl;
import es.um.asio.service.proxy.invoice.InvoiceProxy;
import es.um.asio.service.proxy.invoice.impl.InvoiceProxyImpl;
import es.um.asio.service.proxy.patent.PatentProxy;
import es.um.asio.service.proxy.patent.impl.PatentProxyImpl;
import es.um.asio.service.proxy.person.PersonProxy;
import es.um.asio.service.proxy.person.impl.PersonProxyImpl;
import es.um.asio.service.proxy.project.ProjectProxy;
import es.um.asio.service.proxy.project.impl.ProjectProxyImpl;
import es.um.asio.service.proxy.university.UniversityProxy;
import es.um.asio.service.proxy.university.impl.UniversityProxyImpl;
import es.um.asio.service.service.book.BookService;
import es.um.asio.service.service.invoice.InvoiceService;
import es.um.asio.service.service.patent.PatentService;
import es.um.asio.service.service.person.PersonService;
import es.um.asio.service.service.project.ProjectService;
import es.um.asio.service.service.university.UniversityService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = { ProjectController.class, PatentController.class, BookController.class })
public class WebApplicationStepDefinitionsTest {

	Pageable pageable;

	// PROJECTS
	@Autowired
	@MockBean
	ProjectProxy proxyProject;

	@MockBean
	ProjectService serviceProject;

	ProjectFilter filterProject;

	// PATENT
	@Autowired
	@MockBean
	PatentProxy proxyPatent;

	@MockBean
	PatentService servicePatent;

	PatentFilter filterPatent;

	// BOOK
	@Autowired
	@MockBean
	BookProxy proxyBook;

	@MockBean
	BookService serviceBook;

	BookFilter filterBook;

	// UNIVERSITY
	@Autowired
	@MockBean
	UniversityProxy proxyUniversity;

	@MockBean
	UniversityService serviceUniversity;

	UniversityFilter filterUniversity;

	// PERSON
	@Autowired
	@MockBean
	PersonProxy proxyPerson;

	@MockBean
	PersonService servicePerson;

	PersonFilter filterPerson;

	// INVOICE
	@Autowired
	@MockBean
	InvoiceProxy proxyInvoice;

	@MockBean
	InvoiceService serviceInvoice;

	InvoiceFilter filterInvoice;

	@TestConfiguration
	static class WebApplicationStepDefinitionsConfiguration {
		@Bean
		public ProjectProxy projectProxy() {
			return new ProjectProxyImpl();
		}

		@Bean
		public PatentProxy patentProxy() {
			return new PatentProxyImpl();
		}

		@Bean
		public BookProxy bookProxy() {
			return new BookProxyImpl();
		}

		@Bean
		public UniversityProxy universityProxy() {
			return new UniversityProxyImpl();
		}

		@Bean
		public PersonProxy personProxy() {
			return new PersonProxyImpl();
		}

		@Bean
		public InvoiceProxy invoiceProxy() {
			return new InvoiceProxyImpl();
		}
	}

	@Before
	public void setUp() throws Exception {

		pageable = PageRequest.of(1, 5, Sort.by("ASC"));

		// PROJECT
		filterProject = new ProjectFilter();
		filterProject.setTitle("TITLE");
		filterProject.setLanguage("es");

		// Mock StorageType proxy
		Mockito.when(this.proxyProject.findPaginated(filterProject, pageable)).thenAnswer(invocation -> {
			
			List<ProjectDto> contentResult = new ArrayList<>();

			ProjectDto project = new ProjectDto();
			project.setAbbreviation("projectAbbreviation");
//			project.setDescription("projectDescription");
			project.setEndDate("projectEndDate");
//			project.setForeseenJustificationDate("projectForeseenJustificationDate");
			project.setId("projectId");
//			project.setKeyword("projectKeyword");
//			project.setModality("projectModality");
//			project.setNeedsEthicalValidation("projectNeedsEthicalValidation");
			project.setStartDate("projectStartDate");
			project.setStatus("projectStatus");
			project.setTitle("projectTitle");
			
			contentResult.add(project);
			Page<ProjectDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

		// PATENT
		filterPatent = new PatentFilter();
		filterPatent.setTitle("TITLE");
		filterPatent.setLanguage("es");
		// Mock StorageType proxy
		Mockito.when(this.proxyPatent.findPaginated(filterPatent, pageable)).thenAnswer(invocation -> {
			
			List<PatentDto> contentResult = new ArrayList<>();

			PatentDto patent = new PatentDto();
			patent.setId("patentId");
			patent.setDateIssued("patentDateIssued");
			patent.setDoi("patentDoi");
//			patent.setEndDate("patentEndDate");
//			patent.setPageEnd("patentPageEnd");
			patent.setKeyword("patentKeyword");
			patent.setMode("patentMode");
//			patent.setStartDate("patentStartDate");
//			patent.setStartPage("patentStartPage");
			patent.setTitle("patentTitle");
			
			contentResult.add(patent);
			Page<PatentDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

		// BOOK
		filterBook = new BookFilter();
		filterBook.setTitle("Title");
		filterBook.setLanguage("es");
		// Mock StorageType proxy
		Mockito.when(this.proxyBook.findPaginated(filterBook, pageable)).thenAnswer(invocation -> {

			List<BookDto> contentResult = new ArrayList<>();

			BookDto book = new BookDto();
			book.setDate("bookDate");
			book.setDoi("bookDoi");
			book.setEdition("bookEdition");
			book.setEndPage("bookEndPage");
			book.setIccn("bookIccn");
			book.setId("bookId");
			book.setPlaceOfPublication("bookPlaceOfPublication");
			book.setPublishedIn("bookPublishedIn");
			book.setStartPage("bookStartPage");
			book.setTitle("bookTitle");
			
			contentResult.add(book);
			Page<BookDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

		// UNIVERSITY
		filterUniversity = new UniversityFilter();
		filterUniversity.setTitle("NAME");
		filterUniversity.setLanguage("es");
		// Mock proxy
		Mockito.when(this.proxyUniversity.findPaginated(filterUniversity, pageable)).thenAnswer(invocation -> {

			List<UniversityDto> contentResult = new ArrayList<>();

			UniversityDto university = new UniversityDto();
			university.setId("universityId");
			university.setTitle("universityName");
			
			contentResult.add(university);
			Page<UniversityDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

		// PERSON
		filterPerson = new PersonFilter();
		filterPerson.setName("NAME");
		filterPerson.setLanguage("es");
		// Mock proxy
		Mockito.when(this.proxyPerson.findPaginated(filterPerson, pageable)).thenAnswer(invocation -> {

			List<PersonDto> contentResult = new ArrayList<>();

			PersonDto person = new PersonDto();
//			person.setBirthDate("personBirthDate");
//			person.setDescription("personDescription");
			person.setFirstName("personFirstName");
			person.setGender("personGender");
//			person.setHasContactInfo("personHasContactInfo");
//			person.setHomepage("personHomePage");
			person.setId("personId");
//			person.setImage("personImage");
//			person.setName("personName");
			person.setNickname("personNickname");
//			person.setPersonalMaibox("personPersonalMaibox");
			person.setResearchLine("personResearchLine");
			person.setSurname("personSurname");
//			person.setTaxId("personTaxId");
			person.setTitle("personTitle");
			
			contentResult.add(person);
			Page<PersonDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});
		
		// PERSON DETAIL
		filterPerson = new PersonFilter();
		filterPerson.setName("NAME");
		filterPerson.setLanguage("es");
		// Mock proxy
		Mockito.when(this.proxyPerson.find(Mockito.anyString())).thenAnswer(invocation -> {

			List<PersonDetailDto> contentResult = new ArrayList<>();

			PersonDetailDto personDetail = new PersonDetailDto();
			personDetail.setBirthDate("personBirthDate");
			personDetail.setDescription("personDescription");
			personDetail.setFirstName("personFirstName");
			personDetail.setGender("personGender");
			personDetail.setHasContactInfo("personHasContactInfo");
			personDetail.setHomepage("personHomePage");
			personDetail.setId("personId");
			personDetail.setImage("personImage");
			personDetail.setName("personName");
			personDetail.setNickname("personNickname");
			personDetail.setPersonalMaibox("personPersonalMaibox");
			personDetail.setResearchLine("personResearchLine");
			personDetail.setSurname("personSurname");
			personDetail.setTaxId("personTaxId");
			personDetail.setTitle("personTitle");
			
			contentResult.add(personDetail);
			Page<PersonDetailDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

		// INVOICE
		filterInvoice = new InvoiceFilter();
		filterInvoice.setId("NAME");
		filterInvoice.setLanguage("es");
		// Mock proxy
		Mockito.when(this.proxyInvoice.findPaginated(filterInvoice, pageable)).thenAnswer(invocation -> {

			List<InvoiceDto> contentResult = new ArrayList<>();

			InvoiceDto invoice = new InvoiceDto();
			invoice.setDateTime("invoiceDateTime");
			invoice.setDoi("invoiceDoi");
			invoice.setEndPage("invoiceEndPage");
			invoice.setId("invoiceId");
			invoice.setKeyword("invoiceKeyword");
			invoice.setPublishedIn("invoicePublishedIn");
			invoice.setStartPage("invoiceStartPage");
			invoice.setTitle("invoiceTitle");
			
			contentResult.add(invoice);
			Page<InvoiceDto> page = new PageImpl<>(contentResult, pageable, contentResult.size());
			return page;
		});

	}

	@Given("^call to backend project controller$")
	public void call_to_backend_project_controller() {
		assertNotNull(filterProject);
	}

	@Then("^the controller call to servie and find project result to send front$")
	public void the_controller_call_to_servie_and_find_project_result_to_send_front() {

		Page<ProjectDto> page = this.proxyProject.findPaginated(filterProject, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("projectTitle"));
	}

	@Given("^call to backend patent controller$")
	public void call_to_backend_patent_controller() {
		assertNotNull(filterPatent);
	}

	@Then("^the controller call to servie and find patent result to send front$")
	public void the_controller_call_to_servie_and_find_patent_result_to_send_front() {

		Page<PatentDto> page = this.proxyPatent.findPaginated(filterPatent, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("patentTitle"));
	}

	@Given("^call to backend book controller$")
	public void call_to_backend_book_controller() {
		assertNotNull(filterBook);
	}

	@Then("^the controller call to servie and find book result to send front$")
	public void the_controller_call_to_servie_and_find_book_result_to_send_front() {

		Page<BookDto> page = this.proxyBook.findPaginated(filterBook, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("bookTitle"));
	}

	@Given("^call to backend university controller$")
	public void call_to_backend_university_controller() {
		assertNotNull(filterUniversity);
	}

	@Then("^the controller call to servie and find university result to send front$")
	public void the_controller_call_to_servie_and_find_university_result_to_send_front() {

		Page<UniversityDto> page = this.proxyUniversity.findPaginated(filterUniversity, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("universityName"));
	}

	@Then("^the controller call to servie to find and group universities by quality seal to send front$")
	public void the_controller_call_to_servie_to_find_and_group_universities_by_quality_seal_to_send_front() {
		Assume.assumeTrue(true);
	}

	@Then("^the controller call to servie to find and group universities by financing to send front$")
	public void the_controller_call_to_servie_to_find_and_group_universities_by_financing_to_send_front() {
		Assume.assumeTrue(true);
	}

	@Given("^call to backend person controller$")
	public void call_to_backend_person_controller() {
		assertNotNull(filterPerson);
	}

	@Then("^the controller call to servie and find person result to send front$")
	public void the_controller_call_to_servie_and_find_person_result_to_send_front() {

		Page<PersonDto> page = this.proxyPerson.findPaginated(filterPerson, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("personTitle"));
	}

	@Then("^the controller call to servie to find and group persons by type of area to send front$")
	public void the_controller_call_to_servie_to_find_and_group_persons_by_type_of_area_to_send_front() {
		Assume.assumeTrue(true);
	}

	@Given("^call to backend invoice controller$")
	public void call_to_backend_invoice_controller() {
		assertNotNull(filterInvoice);
	}

	@Then("^the controller call to servie and find invoice result to send front$")
	public void the_controller_call_to_servie_and_find_invoice_result_to_send_front() {

		Page<InvoiceDto> page = this.proxyInvoice.findPaginated(filterInvoice, pageable);
		assertNotNull(page);

		assertEquals(true, page.getContent().get(0).getTitle().equals("invoiceTitle"));
	}
}
