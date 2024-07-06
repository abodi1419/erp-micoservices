-- 31-05-2024
INSERT INTO erp.common_term_types (id, term_name, term_name_ar)
VALUES (1, 'Delivery Terms', 'شروط التوصيل');

INSERT INTO erp.common_term_types (id, term_name, term_name_ar)
VALUES (2, 'Payment Terms', 'شروط الدفع');

INSERT INTO erp.common_term_types (id, term_name, term_name_ar)
VALUES (3, 'Warranty Terms', 'شروط الضمان');

--------------------------------------------------------------------------------------------

INSERT INTO erp.common_banks (id, description, description_ar, local, name, name_ar, swift)
VALUES (1, null, null, true, 'Saudi National Bank (SNB)', 'البنك الأهلي السعودي', 'NCBKSAJE');

INSERT INTO erp.common_banks (id, description, description_ar, local, name, name_ar, swift)
VALUES (2, null, null, true, 'Al Rajhi Bank', 'بنك الراجحي', 'RJHISARI');

INSERT INTO erp.common_banks (id, description, description_ar, local, name, name_ar, swift)
VALUES (3, null, null, true, 'Riyad Bank', 'بنك الرياض', 'RIBLSARI');

INSERT INTO erp.common_banks (id, description, description_ar, local, name, name_ar, swift)
VALUES (4, null, null, true, 'Saudi Awwal Bank (SAB)', 'بنك الأول السعودي ساب', 'ALINMASJ');

INSERT INTO erp.common_banks (id, description, description_ar, local, name, name_ar, swift)
VALUES (5, null, null, true, 'Arab National Bank (ANB)', 'بنك العربي الوطني', 'ARNBSARI');

------------------------------------------------------------------------------------

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, true, 'Manufacturer', 'مصنَع');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Distributor', 'موزَع');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Wholesaler', 'بائع جملة');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Retailer', 'بائع تجزئة');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Service Provider', 'مقدم خدمة');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Contractor', 'مقاول');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Consultant', 'استشارات');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Trader', 'تاجر');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Agent', 'وكيل');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Importer', 'مستورد');

INSERT INTO erp.common_business_types (certificate_name, certificate_name_ar, required_certificate, type_of_business,
                                       type_of_business_ar)
VALUES (null, null, false, 'Exporter', 'مصدَر');

----------------------------------------------------------------------------------------------

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Manufacturing', 'التصنيع');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Construction', 'البناء');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Retail', 'البيع بالتجزئه');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Wholesale/Distribution', 'البيع بالجمله/التوزيع');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Transportation/Logistics', 'المواصلات والنقل');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Information Technology', 'تقنية المعلومات');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Healthcare', 'الصحه');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Energy/Utilities', 'الطاقه والمرافق');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Finance/Banking', 'التمويل والخدمات المصرفية');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Professional Services', 'الخدمات الاحترافية');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Education', 'التعليم');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Government/Public Sector', 'القطاع العام والحكومي');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Agriculture', 'المزراعة');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Hospitality', 'الضيافة');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Telecommunications', 'الاتصالات السلكية واللا سلكية');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Aerospace/Defense', 'الفضاء والدفاع');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Automotive', 'السيارات');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Pharmaceutical/Life Sciences', 'الصيدلة وعلوم الحياة');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Consumer Goods', 'بضائع المستهلك');

INSERT INTO erp.common_industry_types (industry, industry_ar)
VALUES ('Industrial/Engineering', 'الهندسة الصناعية');

------------------------------------------------------------------

