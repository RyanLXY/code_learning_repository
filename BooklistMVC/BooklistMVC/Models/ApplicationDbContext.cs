using System;
using Microsoft.EntityFrameworkCore;

namespace BooklistMVC.Models
{
    public class ApplicationDbContext :DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {
        }

        public DbSet<Book> Books { get; set; }
    }
}
