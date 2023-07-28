#include <stdarg.h>
#include <unistd.h>
#include "main.h"

/**
 * _printf - Produces output according to a format
 * @format: The format string
 *
 * Return: The number of characters printed (excluding the null byte used to
 *         end output to strings)
 */
int _printf(const char *format, ...)
{
	va_list args;
	int count = 0;

	va_start(args, format);

	while (*format)
	{
		if (*format != '%')
		{
			_putchar(*format);
			count++;
		}
		else
		{
			format++;
			if (*format == 'c')
			{
				char c = va_arg(args, int);
				_putchar(c);
				count++;
			}
			else if (*format == 's')
			{
				char *str = va_arg(args, char *);
				if (str == NULL)
					str = "(null)";
				while (*str)
				{
					_putchar(*str);
					str++;
					count++;
				}
			}
			else if (*format == 'd' || *format == 'i')
			{
				int num = va_arg(args, int);
				int div = 1;

				if (num < 0)
				{
					_putchar('-');
					count++;
					num *= -1;
				}

				while (num / div > 9)
					div *= 10;

				while (div)
				{
					_putchar(num / div % 10 + '0');
					count++;
					div /= 10;
				}
			}
			else if (*format == '%')
			{
				_putchar('%');
				count++;
			}
			else
			{
				_putchar('%');
				_putchar(*format);
				count += 2;
			}
		}
		format++;
	}

	va_end(args);
	return (count);
}
