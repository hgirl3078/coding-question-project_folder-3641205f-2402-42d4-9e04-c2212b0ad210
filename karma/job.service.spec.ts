import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { JobService } from './job.service';
import { Job } from '../models/job.model';

describe('JobService', () => {
  let service: JobService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [JobService]
    });
    service = TestBed.inject(JobService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch free jobs', () => {
    const mockJobs: Job[] = [
      { jobID: 1, jobTitle: 'Developer', category: 'free' }
    ];

    service.getFreeJobs().subscribe(jobs => {
      expect(jobs).toEqual(mockJobs);
    });

    const req = httpMock.expectOne(`${service.apiUrl}/free-jobs`);
    expect(req.request.method).toBe('GET');
    req.flush(mockJobs);
  });
});
