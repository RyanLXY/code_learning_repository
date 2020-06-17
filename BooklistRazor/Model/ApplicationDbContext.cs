using System;
using Microsoft.EntityFrameworkCore;

namespace BooklistRazor.Model
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }
        public DbSet<Book> Book { get; set; }
        
    }
}
