package exchangeOfficePAO.service;

import exchangeOfficePAO.models.*;

import java.io.*;
import java.util.ArrayList;
import exchangeOfficePAO.exceptions.JobNotFoundException;
import exchangeOfficePAO.repository.CurrenciesRepository;

import java.util.Date;
import java.util.List;

public class IOService {
    private static IOService IOService = null;

    private IOService(){}

    public static IOService getInstance(){
        if(IOService == null)
            IOService = new IOService();
        return IOService;
    }

    //the format will be the number of the employees first and then the employees
    public EmployeeService readEmployees(String filepath) throws IOException, JobNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
        int numberOfEmployees = 0;
        EmployeeService employeeService = new EmployeeService();
        String row = null, data[] = null;
        List<String> JOBS = new ArrayList<>(List.of("cashier", "guardian", "janitor", "manager", "promoter", "supervisor"));
        int jobIndex = -1;
        row = csvReader.readLine();
        numberOfEmployees = Integer.parseInt(row);
        for(int actualRowNr = 0; actualRowNr < numberOfEmployees; actualRowNr++){
            row = csvReader.readLine();
            data = row.split(",");
            jobIndex = JOBS.indexOf(data[0]);
            if(jobIndex != -1){
                switch (jobIndex){
                    case 0: employeeService.addEmployee(new Cashier(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4])));
                            break;
                    case 1: employeeService.addEmployee(new Guardian(data[1], data[2], Integer.parseInt(data[3])));
                            break;
                    case 2: employeeService.addEmployee(new Janitor(data[1], data[2], Integer.parseInt(data[3])));
                            break;
                    case 3: employeeService.addEmployee(new Manager(data[1], data[2], Integer.parseInt(data[3])));
                            break;
                    case 4: employeeService.addEmployee(new Promoter(data[1], data[2], Integer.parseInt(data[3])));
                            break;
                    case 5: employeeService.addEmployee(new Supervisor(data[1], data[2], Integer.parseInt(data[3])));
                            break;
                    default:
                        break;

                }
            }
            else
                throw new JobNotFoundException("In the file is a job type that doesn't correspond to the application!");
        }
        csvReader.close();
        return employeeService;
    }

    public void writeEmployees(String filepath, EmployeeService employeeService) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath, false);
        List<String> JOBS = new ArrayList<>(List.of("cashier", "guardian", "janitor", "manager", "promoter", "supervisor"));
        fileWriter.append(String.valueOf(employeeService.getEmployeeRepository().size()));
        fileWriter.append('\n');
        for(Employee employee : employeeService.getEmployeeRepository()){
            if(employee instanceof Cashier){
                fileWriter.append(JOBS.get(0));
                fileWriter.append(',');
                fileWriter.append(employee.getFirstName());
                fileWriter.append(',');
                fileWriter.append(employee.getLastName());
                fileWriter.append(',');
                fileWriter.append(String.valueOf(employee.getExperience()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(((Cashier) employee).getDeskNo()));
                fileWriter.append('\n');
            }
            else{
                if(employee instanceof Guardian){
                    fileWriter.append(JOBS.get(1));
                    fileWriter.append(',');
                    fileWriter.append(employee.getFirstName());
                    fileWriter.append(',');
                    fileWriter.append(employee.getLastName());
                    fileWriter.append(',');
                    fileWriter.append(String.valueOf(employee.getExperience()));
                    fileWriter.append('\n');
                }
                else{
                    if(employee instanceof Janitor){
                        fileWriter.append(JOBS.get(2));
                        fileWriter.append(',');
                        fileWriter.append(employee.getFirstName());
                        fileWriter.append(',');
                        fileWriter.append(employee.getLastName());
                        fileWriter.append(',');
                        fileWriter.append(String.valueOf(employee.getExperience()));
                        fileWriter.append('\n');
                    }
                    else{
                        if(employee instanceof Manager){
                            fileWriter.append(JOBS.get(3));
                            fileWriter.append(',');
                            fileWriter.append(employee.getFirstName());
                            fileWriter.append(',');
                            fileWriter.append(employee.getLastName());
                            fileWriter.append(',');
                            fileWriter.append(String.valueOf(employee.getExperience()));
                            fileWriter.append('\n');
                        }
                        else{
                            if(employee instanceof Promoter){
                                fileWriter.append(JOBS.get(4));
                                fileWriter.append(',');
                                fileWriter.append(employee.getFirstName());
                                fileWriter.append(',');
                                fileWriter.append(employee.getLastName());
                                fileWriter.append(',');
                                fileWriter.append(String.valueOf(employee.getExperience()));
                                fileWriter.append('\n');
                            }
                            else{
                                if(employee instanceof Supervisor){
                                    fileWriter.append(JOBS.get(5));
                                    fileWriter.append(',');
                                    fileWriter.append(employee.getFirstName());
                                    fileWriter.append(',');
                                    fileWriter.append(employee.getLastName());
                                    fileWriter.append(',');
                                    fileWriter.append(String.valueOf(employee.getExperience()));
                                    fileWriter.append('\n');
                                }
                            }
                        }
                    }
                }
            }
        }
        fileWriter.close();
    }

    public ClientService readClients(String filepath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
        int numberOfClients = 0;
        String row = null, data[] = null, firstName = null, lastName = null;
        Address address = null;
        row = csvReader.readLine();
        numberOfClients = Integer.parseInt(row);
        ClientService clientService = new ClientService();
        for(int rowNr = 0; rowNr < numberOfClients; rowNr++){
            row = csvReader.readLine();
            data = row.split(",");
            firstName = data[0];
            lastName = data[1];
            if (Integer.parseInt(data[2]) == 0) {

                address = new Address(data[3], Integer.parseInt(data[4]));
                clientService.addClient(new Client(firstName, lastName, address, data[5]));
            }
            else {
                address = new Address(data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]));
                clientService.addClient(new Client(firstName, lastName, address, data[8]));
            }
        }
        csvReader.close();
        return clientService;
    }

    public void writeClients(String filepath, ClientService clientService) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.append(String.valueOf(clientService.getClienti().size()));
        fileWriter.append('\n');
        for(Client client : clientService.getClienti()){
            fileWriter.append(client.getFirstName());
            fileWriter.append(',');
            fileWriter.append(client.getLastName());
            fileWriter.append(',');
            fileWriter.append(client.getAddress().getStreet());
            fileWriter.append(',');
            fileWriter.append(String.valueOf(client.getAddress().getNumberStreet()));
            if(client.getAddress().getTip() == 1){
                fileWriter.append(',');
                fileWriter.append(String.valueOf(client.getAddress().getNumberBloc()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(client.getAddress().getFloor()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(client.getAddress().getNumberApartment()));
            }
            fileWriter.append('\n');
        }
        fileWriter.close();
    }

    public CurrenciesService readCurrencies(String filepath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
        CurrenciesService currenciesService = new CurrenciesService();
        int numberOfCurrencies = 0;
        String row = null, data[] = null;
        row = csvReader.readLine();
        data = row.split(",");
        numberOfCurrencies = Integer.parseInt(data[0]);
        Currency.setNumberOfCurrencies(Integer.parseInt(data[1]));
        for(int i = 0; i < numberOfCurrencies; i++){
            data = row.split(",");
            currenciesService.addCurrency(new Currency(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4])));
        }
        csvReader.close();
        return currenciesService;
    }

    public void writeCurrencies(String filepath, CurrenciesService currenciesService) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.append(String.valueOf(currenciesService.getCurrenciesRepository().size()));
        fileWriter.append(',');
        fileWriter.append(String.valueOf(Currency.getNumberOfCurrencies()));
        fileWriter.append('\n');
        for(Currency currency : currenciesService.getCurrenciesRepository()){
            fileWriter.append(currency.getName());
            fileWriter.append(',');
            fileWriter.append(String.valueOf(currency.getSellPrice()));
            fileWriter.append(',');
            fileWriter.append(String.valueOf(currency.getBuyPrice()));
            fileWriter.append(',');
            fileWriter.append(String.valueOf(currency.getAvailable()));
            fileWriter.append(',');
            fileWriter.append(String.valueOf(currency.getId()));
            fileWriter.append('\n');
        }
        fileWriter.close();
    }

    public TransactionService readTransactions(String filepath) throws IOException{
        BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
        String row = null, data[] = null;
        TransactionService transactionService = new TransactionService();
        int numberOfTransactions = 0;
        row = csvReader.readLine();
        data = row.split(",");
        numberOfTransactions = Integer.parseInt(data[0]);
        Transaction.setNumberOfTransactions(Integer.parseInt(data[1]));
        for(int i = 0; i < numberOfTransactions; i++){
            row = csvReader.readLine();
            data = row.split(",");
            transactionService.addTransaction(new Transaction(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), new Date(Long.parseLong(data[4])), Integer.parseInt(data[5])));
        }
        csvReader.close();
        return transactionService;
    }

    public void writeTransactions(String filepath, TransactionService transactionService) throws IOException{
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.append(String.valueOf(transactionService.getTransactionRepository().size()));
        fileWriter.append(',');
        fileWriter.append(String.valueOf(Transaction.getNumberOfTransactions()));
        fileWriter.append('\n');
        for(Transaction transaction : transactionService.getTransactionRepository()){
            fileWriter.append(String.valueOf(transaction.getTip()));
            fileWriter.append(',');
            fileWriter.append(transaction.getCNP());
            fileWriter.append(',');
            fileWriter.append(String.valueOf(transaction.getCurrencyId()));
            fileWriter.append(',');
            fileWriter.append(String.valueOf(transaction.getValue()));
            fileWriter.append(',');
            fileWriter.append(String.valueOf(transaction.getDate().getTime()));
        }
        fileWriter.close();
    }

    public CurrenciesHistoryService readCurrenciesHistory(String filepath) throws IOException{
        BufferedReader csvReader = new BufferedReader(new FileReader(filepath));
        String row = null, data[] = null;
        CurrenciesHistoryService currenciesHistoryService = new CurrenciesHistoryService();
        row = csvReader.readLine();
        int numberOfDaysHistory = Integer.parseInt(row), numberOfCurrencies = 0;
        Date date = null;
        CurrenciesRepository currenciesRepository = null;
        for(int i = 0; i < numberOfDaysHistory; i++){
            row = csvReader.readLine();
            data = row.split( ",");
            date = new Date(Long.parseLong(data[0]));
            numberOfCurrencies = Integer.parseInt(data[1]);
            List<Currency> currencyList = new ArrayList<>();
            for(int nr = 0; nr < numberOfCurrencies; nr++){
                row = csvReader.readLine();
                data = row.split(",");
                currencyList.add(new Currency(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), Integer.parseInt(data[4])));
            }
            currenciesHistoryService.addCurrenciesInHistory(date, currencyList);
        }
        csvReader.close();
        return currenciesHistoryService;
    }

    public void writeCurrenciesHistory(String filepath, CurrenciesHistoryService currenciesHistoryService) throws IOException{
        FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.append(String.valueOf(currenciesHistoryService.getAllHistory().size()));
        fileWriter.append('\n');
        for(Date date : currenciesHistoryService.getAllHistory().keySet()){
            fileWriter.append(String.valueOf(date.getTime()));
            fileWriter.append(',');
            CurrenciesRepository currenciesRepository = currenciesHistoryService.getHistoryDay(date);
            fileWriter.append(String.valueOf(currenciesRepository.getCurrencyList().size()));
            fileWriter.append('\n');
            for(Currency currency : currenciesRepository.getCurrencyList()){
                fileWriter.append(currency.getName());
                fileWriter.append(',');
                fileWriter.append(String.valueOf(currency.getSellPrice()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(currency.getBuyPrice()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(currency.getAvailable()));
                fileWriter.append(',');
                fileWriter.append(String.valueOf(currency.getId()));
                fileWriter.append('\n');
            }
        }
        fileWriter.close();
    }
}
