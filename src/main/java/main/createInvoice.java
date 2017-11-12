package main;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import main.model.item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class createInvoice {

    String date;

    String customer1;
    String gstin1;
    String address1;
    String state1;
    String stateCode1;
    String phoneNo1;

    String customer2;
    String address2;
    String gstin2;
    String state2;
    String stateCode2;
    String phoneNo2;

    String transport;
    String transportMode;
    String invoiceNo;

    ArrayList<item> items;

    String otherCharges;

    public createInvoice(String date,String customer1, String gstin1, String address1, String state1, String stateCode1, String phoneNo1, String customer2, String address2, String gstin2, String state2, String stateCode2, String phoneNo2, String transport, String transportMode, String invoiceNo, ArrayList<item> items,String otherCharges) {
        this.date = date;
        this.customer1 = customer1;
        this.gstin1 = gstin1;
        this.address1 = address1;
        this.state1 = state1;
        this.stateCode1 = stateCode1;
        this.phoneNo1 = phoneNo1;
        this.customer2 = customer2;
        this.address2 = address2;
        this.gstin2 = gstin2;
        this.state2 = state2;
        this.stateCode2 = stateCode2;
        this.phoneNo2 = phoneNo2;
        this.transport = transport;
        this.transportMode = transportMode;
        this.invoiceNo = invoiceNo;
        this.items = items;
        this.otherCharges = otherCharges;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer1() {
        return customer1;
    }

    public void setCustomer1(String customer1) {
        this.customer1 = customer1;
    }

    public String getGstin1() {
        return gstin1;
    }

    public void setGstin1(String gstin1) {
        this.gstin1 = gstin1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getStateCode1() {
        return stateCode1;
    }

    public void setStateCode1(String stateCode1) {
        this.stateCode1 = stateCode1;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getCustomer2() {
        return customer2;
    }

    public void setCustomer2(String customer2) {
        this.customer2 = customer2;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getGstin2() {
        return gstin2;
    }

    public void setGstin2(String gstin2) {
        this.gstin2 = gstin2;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getStateCode2() {
        return stateCode2;
    }

    public void setStateCode2(String stateCode2) {
        this.stateCode2 = stateCode2;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public ArrayList<item> getItems() {
        return items;
    }

    public void setItems(ArrayList<item> items) {
        this.items = items;
    }

    public void createPDF(){

        String userhome = System.getProperty("user.home");
        File shopInvoice = new File(userhome+"\\shop invoice");
        if(!shopInvoice.exists()){
            shopInvoice.mkdir();
        }
        try {
            // Creating a PdfWriter
            String dest = shopInvoice.getAbsolutePath()+"\\"+invoiceNo+".pdf";
            PdfWriter writer = new PdfWriter(dest);

            // Creating a PdfDocument
            PdfDocument pdf = new PdfDocument(writer);
            PageSize ps = PageSize.A4.clone();
            pdf.addNewPage(ps);


            float width = pdf.getDefaultPageSize().getWidth();
            float height = pdf.getDefaultPageSize().getHeight();
            // Define a PdfCanvas instance
            PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
            // Add a rectangle
            canvas.roundRectangle(20, 20, width - 40, height - 40, 5);
            canvas.stroke();
            // Close PdfDocument

            // Creating a Document
            Document document = new Document(pdf);

            Table table = new Table(2);


            Cell cell = new Cell();
            Style gstStyle = new Style();
            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
            gstStyle.setFont(font).setFontSize(15);


            cell.add("GSTIN: 37CUMPM0476N1ZW");
            cell.addStyle(gstStyle);
            cell.setTextAlignment(TextAlignment.LEFT);
            cell.setBorder(Border.NO_BORDER);

            Cell shopname = new Cell();
            shopname.add("CELL: 9505953126");
            shopname.setTextAlignment(TextAlignment.RIGHT);
            shopname.setBorder(Border.NO_BORDER);
            table.addCell(cell);
            table.addCell(shopname);

            Style titleStyle = new Style();
            PdfFont titleFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            titleStyle.setFont(titleFont).setFontSize(25);

            Paragraph title = new Paragraph("M.M. TEXTILES");
            title.addStyle(titleStyle);
            title.setTextAlignment(TextAlignment.CENTER);
            title.setPaddingTop(15f);

            Paragraph address = new Paragraph("Prop: R.Mohan, #4/217, Utlaman Street, Pamidi-515775, Anantapur Dist.(A.P.)");
            address.setTextAlignment(TextAlignment.CENTER);
            address.setPaddingTop(-15f);

            Table invoiceTop = new Table(3);

            Cell invoiceNo = new Cell();
            invoiceNo.add("INVOICE NO : "+invoiceNo);
            invoiceNo.setFontSize(10f);
            invoiceNo.setTextAlignment(TextAlignment.LEFT);
            invoiceNo.setBorder(Border.NO_BORDER);
            invoiceNo.setBorderTop(new SolidBorder(1));
            invoiceNo.setBorderBottom(new SolidBorder(1));


            Cell taxInvoice = new Cell();
            taxInvoice.add("GST INVOICE");
            taxInvoice.setTextAlignment(TextAlignment.CENTER);
            taxInvoice.setBorder(Border.NO_BORDER);
            taxInvoice.setFont(font);
            taxInvoice.setBorderTop(new SolidBorder(1));
            taxInvoice.setBorderBottom(new SolidBorder(1));


            Cell invoiceDate = new Cell();
            invoiceDate.add("INVOICE Date: "+date);
            invoiceDate.setTextAlignment(TextAlignment.RIGHT);
            invoiceDate.setFontSize(10f);
            invoiceDate.setBorder(Border.NO_BORDER);
            invoiceDate.setBorderTop(new SolidBorder(1));
            invoiceDate.setBorderBottom(new SolidBorder(1));


            invoiceTop.setPaddingTop(10f);
            invoiceTop.addCell(invoiceNo);
            invoiceTop.addCell(taxInvoice);
            invoiceTop.addCell(invoiceDate);


            Table shippingTable = new Table(3);

            Cell billedTo = new Cell();
            billedTo.setHeight(100f);
            billedTo.add("Billed to:");
            Style para = new Style();
            para.setFontSize(10f);
            Paragraph billingPara = new Paragraph();
            Paragraph shippingPara = new Paragraph();

            billingPara.setPaddingLeft(20f);
            shippingPara.setPaddingLeft(20f);

            billingPara.addStyle(para);
            shippingPara.addStyle(para);

            billingPara.add("M/s. "+customer1+",\n"+address1+",\nGSTIN: "+gstin1+",\n"+state1+" ("+stateCode1+").");
            shippingPara.add("M/s. "+customer2+",\n"+address2+",\nGSTIN: "+gstin2+",\n"+state2+" ("+stateCode2+").");
            billedTo.add(billingPara);

            Cell shippedTo = new Cell();
            shippedTo.add("Shipped to:");
            shippedTo.setHeight(100f);
            shippedTo.add(shippingPara);


            Cell trasport = new Cell();
            trasport.setHeight(100f);
            Table insideTrans = new Table(2);
            Cell transC1 = new Cell();
            transC1.setFontSize(10f);
            transC1.setBorder(Border.NO_BORDER);
            transC1.add("Trasnport ");
            transC1.add("Date of Supply ");
            transC1.add("Place of Supply  ");
            transC1.add("Station ");


            Cell transC2 = new Cell();
            transC2.add(": "+transport);
            transC2.add(": "+date);
            transC2.add(": "+address2);
            transC2.add(": Pamidi to "+address2);

            transC2.setBorder(Border.NO_BORDER);
            transC2.setFontSize(10f);

            insideTrans.addCell(transC1);
            insideTrans.addCell(transC2);

            trasport.add(insideTrans);


            shippingTable.addCell(billedTo);
            shippingTable.addCell(shippedTo);
            shippingTable.addCell(trasport);

            float[] columnWidths = {15, 50, 25, 25, 25, 40};
            Table itemTable = new Table(columnWidths)
                    .setWidthPercent(100)
                    .setFixedLayout();

            Cell sNoHeader = new Cell();
            sNoHeader.add("SNO.").setTextAlignment(TextAlignment.CENTER);

            Cell descriptionHeader = new Cell();
            descriptionHeader.add("Description").setTextAlignment(TextAlignment.CENTER);

            Cell baleHeader = new Cell();
            baleHeader.add("Bale No.").setTextAlignment(TextAlignment.CENTER);

            Cell qtyHeader = new Cell().setTextAlignment(TextAlignment.CENTER);
            qtyHeader.add("qty").setTextAlignment(TextAlignment.CENTER);
            Cell priceHeader = new Cell();
            priceHeader.add("Price").setTextAlignment(TextAlignment.CENTER);
            Cell amountHeader = new Cell();
            amountHeader.add("Amount").setTextAlignment(TextAlignment.CENTER);


            itemTable.addHeaderCell(sNoHeader);
            itemTable.addHeaderCell(descriptionHeader);
            itemTable.addHeaderCell(baleHeader);
            itemTable.addHeaderCell(qtyHeader);
            itemTable.addHeaderCell(priceHeader);
            itemTable.addHeaderCell(amountHeader);

            Cell sNo = new Cell();
            sNo.setTextAlignment(TextAlignment.CENTER);
            sNo.setHeight(200f);



            Cell des = new Cell();
            des.setHeight(200f);
            des.setTextAlignment(TextAlignment.CENTER);



            Cell baleNo = new Cell();
            baleNo.setHeight(200f);
            baleNo.setTextAlignment(TextAlignment.CENTER);




            Cell qty = new Cell();
            qty.setHeight(200f);
            qty.setTextAlignment(TextAlignment.CENTER);



            Cell price = new Cell();
            price.setHeight(200f);
            price.setTextAlignment(TextAlignment.CENTER);




            Cell amount = new Cell();
            amount.setHeight(200f);
            amount.setTextAlignment(TextAlignment.RIGHT);
            amount.setPaddingRight(10f);

            int count = 0;
            int qtyTotal = 0;
            double amountTotal = 0;
            for(item i : items){
                sNo.add(String.valueOf(++count));
                des.add(i.getProduct());
                baleNo.add(i.getBaleNo());
                qty.add(i.getQty());
                qtyTotal = qtyTotal + Integer.parseInt(i.getQty());
                amountTotal = amountTotal + Double.parseDouble(i.getTotal());
                price.add(i.getPrice());
                amount.add(i.getTotal());
            }

            itemTable.addCell(sNo);
            itemTable.addCell(des);
            itemTable.addCell(baleNo);
            itemTable.addCell(qty);
            itemTable.addCell(price);
            itemTable.addCell(amount);

            float[] columnWidthsTotal = {65, 25, 25,25, 40};
            Table totalTable = new Table(columnWidthsTotal)
                    .setWidthPercent(100)
                    .setFixedLayout();

            Cell TotalCell = new Cell();
            TotalCell.setTextAlignment(TextAlignment.CENTER);
            TotalCell.add("Total");

            Cell noOfBales = new Cell();
            noOfBales.setTextAlignment(TextAlignment.CENTER);
            noOfBales.add(String.valueOf(items.size()));

            Cell noOfItems = new Cell();
            noOfItems.setTextAlignment(TextAlignment.CENTER);
            noOfItems.add(String.valueOf(qtyTotal));

            Cell blankCell = new Cell();
            Cell totalAmount = new Cell();
            totalAmount.setTextAlignment(TextAlignment.RIGHT);
            totalAmount.setPaddingRight(10f);
            totalAmount.add(String.valueOf(amountTotal));



            totalTable.addCell(TotalCell);
            totalTable.addCell(noOfBales);
            totalTable.addCell(noOfItems);
            totalTable.addCell(blankCell);
            totalTable.addCell(totalAmount);

            float[] columnWidthsGst = {90, 90};
            Table gstTable = new Table(columnWidthsGst)
                    .setWidthPercent(100)
                    .setFixedLayout();

            Cell accountsCell = new Cell();
            accountsCell.setTextAlignment(TextAlignment.CENTER);
            accountsCell.add("915010018109450\nIFSC UTIB00000332\n\n915010018109450\nIFSC UTIB0000332");
            accountsCell.setHeight(90f);

            Cell gstcell= new Cell();


            float[] columnWidthsigst = {50,40};
            Table igstTable = new Table(columnWidthsigst)
                    .setWidthPercent(100)
                    .setFixedLayout()
                    .setMarginLeft(-2f)
                    .setMarginRight(-2f);

            Table cgstTable = new Table(columnWidthsigst)
                    .setWidthPercent(100)
                    .setFixedLayout()
                    .setMarginLeft(-2f)
                    .setMarginRight(-2f);

            Table sgstTable = new Table(columnWidthsigst)
                    .setWidthPercent(100)
                    .setFixedLayout()
                    .setMarginLeft(-2f)
                    .setMarginRight(-2f);

            Table extra = new Table(columnWidthsigst)
                    .setWidthPercent(100)
                    .setFixedLayout();


            Cell igst = new Cell();

            igst.add("IGST\t5%");
            igst.setBorder(Border.NO_BORDER);
            igst.setBorderBottom(new SolidBorder(.5f));
            igst.setBorderRight(new SolidBorder(.5f));

            Cell igstValue = new Cell();
            igstValue.setTextAlignment(TextAlignment.RIGHT);
            igstValue.setPaddingRight(10f);

            igstValue.setBorder(Border.NO_BORDER);
            igstValue.setBorderBottom(new SolidBorder(.5f));


            Cell cgst = new Cell();
            cgst.add("CGST\t2.5%");
            cgst.setBorder(Border.NO_BORDER);
            cgst.setBorderBottom(new SolidBorder(.5f));
            cgst.setBorderRight(new SolidBorder(.5f));
            Cell cgstValue = new Cell();
            cgstValue.setTextAlignment(TextAlignment.RIGHT);
            cgstValue.setPaddingRight(10f);

            cgstValue.setBorder(Border.NO_BORDER);
            cgstValue.setBorderBottom(new SolidBorder(.5f));

            Cell sgst = new Cell();
            sgst.add("SGST\t2.5%");
            sgst.setBorder(Border.NO_BORDER);
            sgst.setBorderBottom(new SolidBorder(.5f));
            sgst.setBorderRight(new SolidBorder(.5f));
            Cell sgstValue = new Cell();
            sgstValue.setTextAlignment(TextAlignment.RIGHT);
            sgstValue.setPaddingRight(10f);

            sgstValue.setBorder(Border.NO_BORDER);
            sgstValue.setBorderBottom(new SolidBorder(.5f));

            Cell extraCharges = new Cell();
            extraCharges.add("Other Charges");
            extraCharges.setBorder(Border.NO_BORDER);

            Cell extraValue = new Cell();
            extraValue.setTextAlignment(TextAlignment.RIGHT);
            extraValue.setPaddingRight(10f);

            extraValue.setBorder(Border.NO_BORDER);
            extraValue.setBorderLeft(new SolidBorder(.5f));

            double TotalInvoice = 0;
            double twopointfive = 0;
            double five = 0;
            if(stateCode2.equals("37")){
                twopointfive = (amountTotal*2.5)/100;
                igstValue.add("0.00");
                cgstValue.add(String.valueOf(twopointfive));
                sgstValue.add(String.valueOf(twopointfive));
                extraValue.add(otherCharges);
            }else {
               five = (amountTotal*5)/100;
               igstValue.add(String.valueOf(five));
               cgstValue.add("0.00");
               sgstValue.add("0.00");
               extraValue.add(otherCharges);
            }

            igstTable.addCell(igst);
            igstTable.addCell(igstValue);
            cgstTable.addCell(cgst);
            cgstTable.addCell(cgstValue);
            sgstTable.addCell(sgst);
            sgstTable.addCell(sgstValue);
            extra.addCell(extraCharges);
            extra.addCell(extraValue);



            gstcell.add(cgstTable);
            gstcell.add(sgstTable);
            gstcell.add(igstTable);
            gstcell.add(extra);



            gstTable.addCell(accountsCell);
            gstTable.addCell(gstcell);

            float[] columnWidthsTotalValue = {90,50,40};
            Table invoiceTotal = new Table(columnWidthsTotalValue)
                    .setWidthPercent(100)
                    .setFixedLayout();
            Cell invoiceTotalWords = new Cell();
            invoiceTotalWords.add("Twenty Seven lakh Eighty Seven Thousand Seven Hundreed and Thirty three only");
            invoiceTotalWords.setFontSize(9f);
            Cell invoiceTotalcell = new Cell();
            invoiceTotalcell.add("Invoice Total");
            invoiceTotalcell.setTextAlignment(TextAlignment.LEFT);

            Cell invoiceTotalAmount = new Cell();
            if(stateCode2.equals("37")){
                double value = (twopointfive*2)+ Double.parseDouble(otherCharges)+amountTotal;
                invoiceTotalAmount.add(String.valueOf(value));

            }else {
                double value = (five)+ Double.parseDouble(otherCharges)+amountTotal;
                invoiceTotalAmount.add(String.valueOf(value));
            }

            invoiceTotalAmount.setTextAlignment(TextAlignment.RIGHT);
            invoiceTotalAmount.setPaddingRight(10f);
            invoiceTotal.addCell(invoiceTotalWords);
            invoiceTotal.addCell(invoiceTotalcell);
            invoiceTotal.addCell(invoiceTotalAmount);

            Paragraph form = new Paragraph();
            form.add("For");
            Paragraph formName = new Paragraph();
            PdfFont forNameFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            Style forNameStyle = new Style().setFont(forNameFont).setFontSize(15f);
            formName.add("M.M. TEXTILES");

            formName.addStyle(forNameStyle);
            Cell forCell = new Cell();
            forCell.setBorder(Border.NO_BORDER);
            forCell.setTextAlignment(TextAlignment.RIGHT);
            forCell.setPaddingTop(52f);

            forCell.add(form);
            Cell forNameCell = new Cell();
            forNameCell.setBorder(Border.NO_BORDER);
            forNameCell.add(formName);
            forNameCell.setPaddingTop(50f);
            float[] columnWidthsSignature= {130,20,50};
            Table signature = new Table(columnWidthsSignature)
                    .setWidthPercent(100)
                    .setFixedLayout();

            signature.addCell(new Cell().setBorder(Border.NO_BORDER));
            signature.addCell(forCell);
            signature.addCell(forNameCell);


            Cell bCell = new Cell();
            bCell.add("");
            bCell.setBorder(Border.NO_BORDER);
            Cell signatureCell = new Cell();
            signatureCell.add("Singnature");
            signatureCell.setPaddingTop(30f);
            signatureCell.setBorder(Border.NO_BORDER);
            signatureCell.setTextAlignment(TextAlignment.CENTER);
            float[] columnWidthsSignatureText= {130,70};
            Table signatureText = new Table(columnWidthsSignatureText)
                    .setWidthPercent(100)
                    .setFixedLayout();
            signatureText.addCell(bCell);
            signatureText.addCell(signatureCell);




            document.setMargins(20f, 20f, 20f, 20f);
            document.add(table);
            document.add(title);
            document.add(address);
            document.add(invoiceTop);
            document.add(shippingTable);
            document.add(itemTable);
            document.add(totalTable);
            document.add(gstTable);
            document.add(invoiceTotal);

            document.add(signature);
            document.add(signatureText);

            // Closing the document
            document.close();
            System.out.println("Paragraph added");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
