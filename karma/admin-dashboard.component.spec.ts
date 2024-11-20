import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminDashboardComponent } from './admin-dashboard.component';
import { JobService } from '../services/job.service';
import { ApplicationService } from '../services/application.service';

describe('AdminDashboardComponent', () => {
  let component: AdminDashboardComponent;
  let fixture: ComponentFixture<AdminDashboardComponent>;
  let jobService: jasmine.SpyObj<JobService>;
  let applicationService: jasmine.SpyObj<ApplicationService>;

  beforeEach(async () => {
    const jobSpy = jasmine.createSpyObj('JobService', ['getAllJobs', 'deleteJob']);
    const applicationSpy = jasmine.createSpyObj('ApplicationService', ['updateStatus']);

    await TestBed.configureTestingModule({
      declarations: [ AdminDashboardComponent ],
      providers: [
        { provide: JobService, useValue: jobSpy },
        { provide: ApplicationService, useValue: applicationSpy }
      ]
    }).compileComponents();

    jobService = TestBed.inject(JobService) as jasmine.SpyObj<JobService>;
    applicationService = TestBed.inject(ApplicationService) as jasmine.SpyObj<ApplicationService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should load jobs on init', () => {
    jobService.getAllJobs.and.returnValue(Promise.resolve([]));
    component.ngOnInit();
    expect(jobService.getAllJobs).toHaveBeenCalled();
  });

  it('should update application status', async () => {
    applicationService.updateStatus.and.returnValue(Promise.resolve(true));
    await component.updateApplicationStatus(1, 'ACCEPTED');
    expect(applicationService.updateStatus).toHaveBeenCalledWith(1, 'ACCEPTED');
  });
});
