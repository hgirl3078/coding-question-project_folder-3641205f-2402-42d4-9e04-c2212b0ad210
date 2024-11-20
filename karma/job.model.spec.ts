import { Job } from './job.model';

describe('Job', () => {
  it('should create an instance', () => {
    const job = new Job();
    expect(job).toBeTruthy();
  });

  it('should set properties correctly', () => {
    const job = new Job();
    job.jobID = 1;
    job.jobTitle = 'Developer';
    job.category = 'premium';
    
    expect(job.jobID).toBe(1);
    expect(job.jobTitle).toBe('Developer');
    expect(job.category).toBe('premium');
  });
});
